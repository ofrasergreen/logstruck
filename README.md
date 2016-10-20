```scala
def meaningOfLife(logger: Logger) = {
  ...
  log.info("Answer calculated.", result, duration)
  ...
}
```

One might normally have done something like `log.info(s"Answer calculated in $duration: $result")`? Because if you have a lot of logs and you're using a more powerful logging
backend than a file and _grep_ then you

Configuration
===

Configuration is done in code.

Top-level fields pass in at initialization e.g.

```scala
val log = new Logger(params=Map("service" -> "myService"))
```


Output
===

Default output:
- message
- context - e.g. aW52.br13.chVc
- data
  - other
  - ...
- logLevel
- source
  - file
  - line
  - class


Extras
===

FluentD
--

Text (stdout/err or file)
--

HTTP Manager
---
Listen on port xyz for configuration messages e.g. to change log-level


Other points
===

- not compatible with SL4J

Todo
===

- place filters inside output