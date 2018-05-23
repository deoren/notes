# Go - Misc notes

## Variable names

- Go encourages not predeclaring variables, instead, declaring them within
  the smallest scope possible
- Because you're declaring variables within a small scope, you can use
  short variable names without a lot of context. This is a core idiom
  in Go.
- The farther away a variable is being declared from where it's being
  used, the longer the name has to be (guideline).
  
- The "blank" identifier
    - Allows you to use `_` in place of where you ordinarily are required
      to supply a variable.
    - Useful to indicate that you do not care about a return value from
      a function.

## Scope

### Example: What you might expect


```golang
func main() {

	// Retrieve the user profile.
	u, err := retrieveUser("sally")
	if err != nil {
		fmt.Println(err)
		return
	}

	// Display the user profile.
	fmt.Printf("%+v\n", *u)
}
```

### Example: What you might not expect

From Ultimate Go Programming:

> You might see a combination if statement calling a function and then
> checking, let's say, an error, and moving on. But this is something that's
> really unique in Go. Something that I think is incredibly powerful. Your if 
> statement, your for statement, your switch statement. These statements, they 
> actually come with their own block of scope, something that we don't have in
> other languages.

In this example, the `u` variable has a different scope than the variable
declared outside of the `if` statement.

```golang
func main() {

  var u *user

	// Retrieve the user profile.
	if u, err := retrieveUser("sally"); err != nil {
		fmt.Println(err)
		return
	}

	// Display the user profile.
	fmt.Printf("%+v\n", *u)
}
```

## References

- Ultimate Go Programming
    - https://www.safaribooksonline.com/videos/ultimate-go-programming/9780134757476/
    - http://www.informit.com/store/ultimate-go-programming-livelessons-9780134757483

