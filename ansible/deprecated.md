# Deprecated practices

## Using a loop on a package module via `squash_actions`

From the Ansible 2.7 Porting Guide:

The use of `squash_actions` to invoke a package module, such as `yum`, to only
invoke the module once is deprecated, and will be removed in Ansible 2.11.

Instead of relying on implicit squashing, **tasks should instead supply the list
directly to the `name`, `pkg` or `package` parameter of the module**. This
functionality has been supported in most modules since Ansible 2.3.

OLD In Ansible 2.6 (and earlier) the following task would invoke the `yum`
module only 1 time to install multiple packages

```yaml
- name: Install packages
  yum:
    name: "{{ item }}"
    state: present
  with_items: "{{ packages }}"
```

NEW In Ansible 2.7 it should be changed to look like this:

```yaml
- name: Install packages
  yum:
    name: "{{ packages }}"
    state: present
```

## References

- <https://github.com/deoren/notes>

- <https://docs.ansible.com/ansible/latest/porting_guides/porting_guide_2.7.html#using-a-loop-on-a-package-module-via-squash-actions>
