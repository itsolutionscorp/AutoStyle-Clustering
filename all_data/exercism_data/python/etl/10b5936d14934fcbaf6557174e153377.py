#!/usr/bin/python
transform = lambda old: dict([(value.lower(), key) for key, values in old.items() for value in values])
