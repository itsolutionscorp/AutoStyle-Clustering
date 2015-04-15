# -*- coding: utf-8 -*-

def hey(statement):
    statement = statement.strip()
    if statement.isupper():
        return 'Woah, chill out!'
    if statement.endswith("?"):
        return 'Sure.'
    if not statement:
        return 'Fine. Be that way!'
    return "Whatever."
