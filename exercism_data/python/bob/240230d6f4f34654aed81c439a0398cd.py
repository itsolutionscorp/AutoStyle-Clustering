def hey(statement):
    if is_silence(statement):
        return 'Fine. Be that way!'
    if is_yelling(statement):
        return 'Whoa, chill out!'
    if is_question(statement):
        return 'Sure.'
    else:
        return 'Whatever.'

def is_silence(statement):
    if statement.isspace() or not statement:
        return True

def is_yelling(statement):
    if statement.isupper():
        return True

def is_question(statement):
    if statement.endswith('?'):
        return True
