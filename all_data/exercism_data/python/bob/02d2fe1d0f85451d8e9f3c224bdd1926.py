from __future__ import unicode_literals

def hey(statement):
    if (is_empty(statement)):
        return 'Fine. Be that way!'
    if (is_yelling(statement)):
        return 'Whoa, chill out!'
    if (is_question(statement)):
        return 'Sure.'

    return 'Whatever.'

def is_question(statement):
    if (statement.endswith('?')):
        return True
    return False

def is_yelling(statement):
    if any(c.isalpha() for c in statement):
        if (statement.upper() == statement):
            return True
    return False

def is_empty(statement):
    if (statement.strip() == ''):
        return True
    return False
