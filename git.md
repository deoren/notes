# Git - misc notes/tips

- [Git - misc notes/tips](#git---misc-notestips)
  - [Branches](#branches)
    - [Rename branches](#rename-branches)
  - [Tags](#tags)
    - [Show tags](#show-tags)
    - [Show tag details](#show-tag-details)
    - [Create an annotated tag](#create-an-annotated-tag)
    - [Delete tags](#delete-tags)
      - [Remote](#remote)
      - [Local](#local)
  - [Display changes](#display-changes)
    - [Diff for specific commit](#diff-for-specific-commit)
    - [Diff for last commit](#diff-for-last-commit)
    - [Files modified](#files-modified)
  - [Log display options](#log-display-options)
  - [Display configured remote](#display-configured-remote)
  - [Change configured remote](#change-configured-remote)
    - [one branch](#one-branch)
    - [all branches](#all-branches)
  - [Reset date](#reset-date)
    - [Reset date of last commit](#reset-date-of-last-commit)
    - [Reset date of specific commit](#reset-date-of-specific-commit)
  - [Reset author/email](#reset-authoremail)
    - [Reset author of last commit](#reset-author-of-last-commit)
    - [Reset author on ALL commits](#reset-author-on-all-commits)
  - [Configure cached credentials via `.netrc` (e.g., for automated builds)](#configure-cached-credentials-via-netrc-eg-for-automated-builds)
  - [References](#references)

## Branches

### Rename branches

1. `git branch --move old_branch new_branch`
1. `git checkout new_branch`
1. `git push --set-upstream origin new_branch`
1. `git push origin --delete old_branch`

## Tags

### Show tags

`git tag -l`

### Show tag details

`git show TAG_NAME`

### Create an annotated tag

1. `git log --oneline -n 5`
   - *assuming that five lines is enough to spot the commit you want to tag,
    which is usually a merge commit*
1. `git tag -a v0.1.5 -m "Tagging v0.1.5" GIT_COMMIT_HERE`
1. Push the tag(s)
    - All tags: `git push origin --tags`
    - Just the one: `git push origin TAG_NAME`

### Delete tags

#### Remote

- `git push origin :TAG_NAME`
- `git push -d TAG_NAME`
- `git push --delete origin TAG_NAME`

#### Local

`git tag --delete TAG_NAME`

## Display changes

### Diff for specific commit

- `git diff COMMIT~ COMMIT`
- `git show COMMIT`
  - `git log -p c -1` seems to do the same thing
- `git diff COMMIT^!`
- `git diff-tree -p COMMIT`

### Diff for last commit

- `git show HEAD~1`

### Files modified

- `git show --name-only COMMIT`
- `git diff-tree --no-commit-id --name-only -r COMMIT`

## Log display options

- `git log --all --decorate --oneline --graph`
  - Short commit hash
  - One line log summary
  - graph showing branch merges/activity

- `git log --graph --abbrev-commit --decorate --date=relative --all`
  - lists a graph alongside the normal/detailed log output
  - TODO: List what the other options do

- `git log --graph --abbrev-commit --decorate --format=format:'%C(bold blue)%h%C(reset) - %C(bold green)(%ar)%C(reset) %C(white)%s%C(reset) %C(dim white)- %an%C(reset)%C(bold yellow)%d%C(reset)' --all`
  - TODO: Add description

## Display configured remote

- `git branch -vv`
  - shows info for all branches

Example output:

`$ git branch -vv`

```shell
* initial-dev-env-work e34d574 [origin/initial-dev-env-work] Initial version of mysql2sqlite-dev project
  master               14e28a5 [origin/master] Initial commit
```

- `cat .git/config`
  - tip: look for `[branches]`

## Change configured remote

### one branch

1. `git checkout BRANCH_NAME`
1. `git branch --set-upstream-to REMOTE/BRANCH_NAME`

### all branches

Assuming that pushing changes to the remote is OK, this seems to do the trick:

1. `git push origin -u --all`

Example output:

```shell
Everything up-to-date
Branch 'initial-dev-env-work' set up to track remote branch 'initial-dev-env-work' from 'origin'.
Branch 'master' set up to track remote branch 'master' from 'origin'.

```

## Reset date

### Reset date of last commit

1. `git commit --amend --date=now`

### Reset date of specific commit

placeholder

## Reset author/email

### Reset author of last commit

1. `git commit --amend --reset-author --no-edit`

This resets commit author values using the information specified by
`git config user.name` and `git config user.email`.

### Reset author on ALL commits

```shell
git filter-branch -f --env-filter "
    GIT_AUTHOR_NAME='Newname'
    GIT_AUTHOR_EMAIL='new@email'
    GIT_COMMITTER_NAME='Newname'
    GIT_COMMITTER_EMAIL='new@email'
  " HEAD
```

## Configure cached credentials via `.netrc` (e.g., for automated builds)

This approach is useful if you want to setup a build system that requires
read-only access to a restricted repo. Instead of hard-coding your main
set of credentials (which are likely protected by 2FA), you can often
request an API key via the web UI for select repo operations.

1. Create `$HOME/.netrc`
1. Enter `machine`, `login` and `password` details
1. Test!

Example contents:

```bash
machine example.visualstudio.com
login bob
password afg87afg8a7fga8sfg78afga8fgafs8dgsdfagxgcxcfgfrg87as
```

`git clone` operations should work as expected without prompting for
credentials.

## References

- <https://stackoverflow.com/questions/4847101/git-which-is-the-default-configured-remote-for-branch>
- <https://stackoverflow.com/questions/9110310/update-git-commit-author-date-when-amending>
- <https://stackoverflow.com/questions/750172/how-to-change-the-author-and-committer-name-and-e-mail-of-multiple-commits-in-gi>
- <https://dev.to/drews256/i-love-git-log-off>

- <https://stackoverflow.com/questions/5343068/is-there-a-way-to-skip-password-typing-when-using-https-on-github>
- <https://confluence.atlassian.com/bitbucketserver/permanently-authenticating-with-git-repositories-776639846.html>
- <https://help.github.com/articles/creating-a-personal-access-token-for-the-command-line/>
- <https://gist.github.com/technoweenie/1072829>
- <https://gist.github.com/sahilsk/ce21c39a6c2dbc2cd984>

- <https://stackoverflow.com/questions/4082126/git-log-of-a-single-revision>
- <https://stackoverflow.com/questions/17563726/how-to-see-the-changes-in-a-git-commit>
