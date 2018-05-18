# Scratch notes for Clojure

I'm learning *just enough* Clojure in order to give Riemann a try. The scratch
notes below are from watching/reading various sources in an effort to wrap my
head around Clojure.

## `do`

If you want to do more than one thing in the context of the branches of the if,
we can use a special construct called `do`. `do` will let us do more than one
thing, e.g., evaluate more than one expression.

Normally you would only use `do` to invoke a side-effect of some sort, something
like printing to a console, writing to a database, reading from a file or something
else that doesn't involve just the input and output of your function.

This is something that functional programming tries to avoid with varying degrees
of success. If you can avoid using `do`, you probably should. Your code could 
probably be better represented by a pipeline of values into values, but for things
like logging, this is considered a perfectly reasonable thing to do.
