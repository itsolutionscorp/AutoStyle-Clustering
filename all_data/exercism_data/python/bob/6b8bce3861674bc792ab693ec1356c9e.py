def hey(statement):
    if is_silence(statement):
        return 'Fine. Be that way!'
    elif is_yelling(statement):
        return 'Whoa, chill out!'
    elif is_question(statement):
        return 'Sure.'
    else:
        return 'Whatever.'

def is_silence(statement):
    if statement.isspace() or statement == '':
        return True

def is_yelling(statement):
    if statement == statement.upper() and statement != statement.lower():
        return True

def is_question(statement):
    if statement[-1] == '?':
        return True
