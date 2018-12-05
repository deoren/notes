# PowerShell

## Basics

### Boolean values

- non-zero length string evaluates to `$True`

## Active Directory

### Groups

Placeholder for snippets illustrating how to search for groups matching
a very specific pattern and then add a specific user account to said groups.

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
