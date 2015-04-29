def hey(statement):
    if is_yell(statement):
        return 'Whoa, chill out!'
    elif is_question(statement):
        return 'Sure.'
    elif is_nothing(statement):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'

def is_question(statement):
    return statement[-1:] == '?'

def is_yell(statement):
    return statement.isupper()

def is_nothing(statement):
    return statement.lstrip('\t\n ') == ''
