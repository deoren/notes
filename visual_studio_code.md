# Visual Studio Code

## Search / Replace

### Regular Expression

```regex
// Example string to match against
"{{ passenger_standalone_service_account_name }}"

// Search pattern
passenger_standalone_([a-z_]+) }}

// Replace pattern
passenger_standalone['$1'] }}

// Example result of search and replace
"{{ passenger_standalone['service_account_name'] }}"
```

## Plugins

Various plugins that I use or plan on testing/using in the future.

### Used

- Python
- PowerShell
- reStructueredText
- SVN
- Go
- GitLens
- Docker
- C/C++
- systemd-unit-file
- unicode-Substitutions

### Review

- markdownlint
- MySQL
- nginx.conf
- Chef Extension
- Apache Conf
- mssql
- XML Tools
- YAML Support by Red Hat
- Terraform
- Ruby
- REST Client
- HAProxy
- Ansible

### Installation

1. `code --list-extensions`
1. `code --install-extension EXTENSION_ID`

## References

- <https://code.visualstudio.com/docs/editor/extension-gallery>
- <https://marketplace.visualstudio.com/items?itemName=GlenBuktenica.unicode-substitutions>
