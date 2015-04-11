def hey(argument):
    if _is_empty(argument):
        return 'Fine. Be that way!'
    elif _is_shouting(argument):
        return 'Woah, chill out!'
    elif _is_question(argument):
        return 'Sure.'
    else:
        return 'Whatever.'

def _is_empty(argument):
    return len(argument) == 0 or argument.isspace()

def _is_shouting(argument):
    return argument.isupper()

def _is_question(argument):
    return argument.endswith('?')
