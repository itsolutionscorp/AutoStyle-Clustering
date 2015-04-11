# -*- coding: utf-8 -*-

def hey(statement):
    statement = statement.strip()
    if not statement:
        return 'Fine. Be that way!'
    if [char for char in statement if char.lower() != char.upper()] and statement.isupper():
        return 'Woah, chill out!'
    if statement.endswith("?"):
        return 'Sure.'
    return "Whatever."
