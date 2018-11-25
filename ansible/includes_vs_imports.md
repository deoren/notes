# `include_` vs `import_`

## Overview

*The content below was taken from a mix of GitHub comments by Ansible team
member [bcoca](https://github.com/bcoca) and the official docs*

- `import_`
  - no keywords apply to it
  - it always happens
  - any keywords are inherited by contained tasks.
  - This will have the behaviour of ignoring tags on itself and NOT condition
    execution based on them.
  - "static"
    - templating not needed
    - loops not needed
    - conditionals not needed
    - included tasks show when using the `-list` options.
    - Handlers are made available to the whole play.
    - Since Ansible 2.7
      - variables defined in vars and defaults for the role are exposed at
        playbook parsing time.
      - variables will be accessible to roles and tasks executed before the
        location of the import_role task.
      - unlike `include_role`, variable exposure is not configurable, and will
        always be exposed.

- `include_`
  - "dynamic"
  - keywords apply to it
  - keywords are NOT inherited by contained tasks
  - this will NOT ignore tags and WILL condition it's execution based on them
    - templating applied
    - loops supported
    - conditionals supported
    - included tasks DO NOT show when using the `--list` options.
    - Since Ansible 2.7
      - a new module argument named `public` was added to the `include_role`
        module that dictates whether or not the role's `defaults` and `vars`
        will be exposed outside of the role, allowing those variables to be used
        by later tasks. This value defaults to `public: False`, matching current
        behavior.
    - `apply` task argument that allows included tasks to inherit explicitly
      provided attributes

> You can get mostly what `include` (deprecated) did with `include_` by adding
`tags: ['always']` to ensure the `include_` executes by default no matter what
tags you specify. There are other workarounds using `block` or just going back
to `include` which we want to eventually remove but not until all cases are
covered by the new actions

Some snippets from the current
[Tags](https://docs.ansible.com/ansible/latest/user_guide/playbooks_tags.html)
documentation:

- There is no way to 'import only these tags'; you probably want to split into
  smaller roles/includes if you find yourself looking for such a feature.

- To use tags with tasks and roles intended for dynamic inclusions, all needed
  tasks should be explicitly tagged at the task level; or block: may be used to
  tag more than one task at once. The include itself should also be tagged.

## Problem description

As of Ansible 2.7.2 I found that when using a Playbook structure like the
following that none of the tasks would be executed when calling the playbook with
a specific tag (with the intention to only execute tasks with that tag) like
this:

```bash
$ ansible-playbook mass-updates.yml -u sysadmin --tags report --limit "testing"
```

The more I test this the more the suggested "fix" seems to be needed: make
sure to tag the `include_` statment in addition to any tasks. If you use
different tags, you will need to specify both the outer tag(s) for the
`include_` statement in addition to whatever tasks you wish to execute.

## Example playbook and role snippets

See <https://github.com/deoren/ansible-examples/commits/i34196>

## References

### Official docs

- <https://docs.ansible.com/ansible/latest/user_guide/playbooks_tags.html>
- <https://docs.ansible.com/ansible/latest/modules/include_role_module.html>
- <https://docs.ansible.com/ansible/latest/modules/import_role_module.html>
- <https://docs.ansible.com/ansible/latest/modules/include_tasks_module.html>
- <https://docs.ansible.com/ansible/latest/modules/import_tasks_module.html>

- <https://docs.ansible.com/ansible/latest/porting_guides/porting_guide_2.5.html#dynamic-includes-and-attribute-inheritance>
- <https://docs.ansible.com/ansible/latest/porting_guides/porting_guide_2.7.html#include-role-and-import-role-variable-exposure>

### Official GitHub issues

These are the most relevant:

- [Tasks in an 'include_tasks' are not run when specifying tags](https://github.com/ansible/ansible/issues/30882)
  - <https://github.com/ansible/ansible/issues/30882#issuecomment-380596557>
  - <https://github.com/ansible/ansible/issues/30882#issuecomment-399481351>
- [include_role: does not respect tags](https://github.com/ansible/ansible/issues/34196)

These are potentially relevant:

- <https://github.com/ansible/ansible/issues/38262#issuecomment-378754161>
- <https://github.com/ansible/ansible/issues/32015>
- <https://github.com/ansible/ansible/issues/35459>
- <https://github.com/ansible/ansible/pull/39236>
