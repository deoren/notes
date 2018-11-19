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

- `include_`
  - keywords apply to it
  - keywords are NOT inherited by contained tasks
  - This will NOT ignore tags and WILL condition it's execution based on them

> You can get mostly what `include` (deprecated) did with `include_` by adding
`tags: ['always']` to ensure the `include_` executes by default no matter what
tags you specify. There are other workarounds using `block` or just going back
to `include` which we want to eventually remove but not until all cases are
covered by the new actions

## Problem description

As of Ansible 2.7.2 I found that when using a Playbook structure like the
following that none of the tasks would be executed when calling the playbook with
a specific tag (with the intention to only execute tasks with that tag) like
this:

```bash
$ ansible-playbook mass-updates.yml -u sysadmin --tags report --limit "test1*"
```

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

