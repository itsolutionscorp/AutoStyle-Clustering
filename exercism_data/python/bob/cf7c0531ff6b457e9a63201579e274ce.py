import re


def hey(statement):
    statement = re.sub('\s', '', statement)
    if len(statement) == 0:
        return 'Fine. Be that way!'
    elif is_shouting(statement):
        return 'Whoa, chill out!'
    elif is_asking(statement):
        return 'Sure.'
    else:
        return 'Whatever.'


def is_shouting(statement):
    if statement.upper() == statement and re.match('[A-Z]+', statement):
        return True
    return False


def is_asking(statement):
    return re.match(r'.*\?$', statement, re.UNICODE)
