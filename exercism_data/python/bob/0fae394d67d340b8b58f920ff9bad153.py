import re

def is_question(stmt):
    return stmt.endswith('?')

def is_yelling(stmt):
    return stmt == stmt.upper() and not is_junk(stmt) and not is_whitespace(stmt)

def is_junk(stmt):
    return re.match('^[ ,\d\?]+$', stmt)

def is_whitespace(stmt):
    return re.match('^\s*$', stmt)

def hey(stmt):
    if is_yelling(stmt):
        return 'Woah, chill out!'
    elif is_question(stmt):
        return 'Sure.'
    elif is_whitespace(stmt):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
