#!/usr/bin/env python3
import re


def hey(statement):
    statement = statement.strip()
    if not statement:
        return 'Fine. Be that way!'
    stripped = re.sub(r'[\W\s\d]', '', statement)
    if stripped and (stripped == stripped.upper()):
        return 'Whoa, chill out!'
    elif statement.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
