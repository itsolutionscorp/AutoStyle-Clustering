# -*- coding: utf-8 -*-

def hey(statement):
    statement = statement.strip()
    if not statement:
        return 'Fine. Be that way!'
    elif statement.isupper():
        return 'Whoa, chill out!'
    elif statement.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
