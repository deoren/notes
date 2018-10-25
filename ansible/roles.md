# Ansible

## Anatomy of a role

```yaml
- roles
  - starter
    - defaults
      - main.yml
    - files
    - handlers
      - main.yml
    - meta
      - main.yml
    - README.md
    - tasks
      - main.yml
    - templates
    - tests
      - inventory
      - test.yml
    - vars
      - main.yml
```

Each role has a separate subdirectory beneath the `roles` directory with the
structure shown above. In this case, this is for the `starter` role.

Explanation of the directory structure:

Item/Path | Description
------------ | -------------
`defaults/main.yml` | Default properties/variables
`files` | Static files to be transfered to hosts as-is (no templating)
`handlers/main.yml` | actions to take on events
`meta/main.yml` | Metadata for the role (e.g., where to define dependencies on other roles)
`README.md` | Documentation for this role
`tasks/main.yml` | actions to take using modules
`templates` | files generated dynamically
`tests` | Tests that prove this role works as intended
`vars/main.yml` | role specific variables

## Creating a role

### Create a new role using `ansible-galaxy`

`ansible-galaxy init --offline --init-path roles/ apache`

### Output from creating a new role

```shell
placeholder
```

## References

- Ultimate Ansible Bootcamp by School of Devops
  - [Safari Books Online](https://www.safaribooksonline.com/videos/ultimate-ansible-bootcamp/9781789345131)
  - [Packt Publishing](https://www.packtpub.com/application-development/ultimate-ansible-bootcamp-school-devops-video)
