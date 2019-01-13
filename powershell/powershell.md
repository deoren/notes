# PowerShell

## Basics

### Boolean values

- non-zero length string evaluates to `$True`

## Active Directory

### Groups

Placeholder for snippets illustrating how to search for groups matching
a very specific pattern and then add a specific user account to said groups.

## Windows-specific

### Get list of updates installed today

*This comes in handy when preparing a very basic report just prior to closing
out patch tickets*

```PowerShell
Get-HotFix | Where-Object {$_.InstalledOn -gt (Get-Date).AddDays(-1) }
```

Example output:

```ShellSession
Source        Description      HotFixID      InstalledBy          InstalledOn
------        -----------      --------      -----------          -----------
MSSQL1         Update           KB4480063     NT AUTHORITY\SYSTEM  1/13/2019 12:00:00 AM
MSSQL1         Security Update  KB4483187     NT AUTHORITY\SYSTEM  1/13/2019 12:00:00 AM
MSSQL1         Security Update  KB4480970     NT AUTHORITY\SYSTEM  1/13/2019 12:00:00 AM
```

## Tips / Tricks

### Create "checksum" file for each zip file

This example is intended to be easily modified to handle other or additional
file types.

`ls *.zip | % {Get-FileHash $_ | Format-List | Out-File "$($_).checksum.txt" }`

## References

- Active Directory
  - <https://blogs.msdn.microsoft.com/adpowershell/2009/04/03/active-directory-powershell-advanced-filter/>
  - <https://blogs.msdn.microsoft.com/adpowershell/2009/04/14/active-directory-powershell-advanced-filter-part-ii/>

- Boolean/Conditional
  - <https://poshoholic.com/2007/09/13/essential-powershell-beware-of-promiscuous-types/>

- Windows Updates
  - <https://blogs.technet.microsoft.com/mu/2016/11/23/how-to-list-updates-that-have-been-installed-on-your-windows-server-2016-machine/>
  - <https://blogs.technet.microsoft.com/heyscriptingguy/2013/01/31/powertip-find-windows-hotfixes-installed-after-a-certain-date/>
  - <https://blogs.technet.microsoft.com/heyscriptingguy/2014/12/16/use-powershell-to-find-hotfixes-installed-in-time-range/>
