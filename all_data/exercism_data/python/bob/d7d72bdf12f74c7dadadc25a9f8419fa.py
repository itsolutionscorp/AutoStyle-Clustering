# -*- coding: utf-8 -*-

def hey(statement):
    statement = statement.strip()
    if not statement:
        return 'Fine. Be that way!'
    if statement.isupper():
        return 'Woah, chill out!'
    if statement.endswith("?"):
        return 'Sure.'
    return "Whatever."
