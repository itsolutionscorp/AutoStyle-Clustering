def nothing(arg):
    return True if arg is None or arg.strip() == '' else False


def shouting(arg):
    return True if arg.isupper() else False


def question(arg):
    return True if arg.strip().endswith('?') else False


def hey(arg=None):
    response = 'Whatever.'
    if nothing(arg):
        response = 'Fine. Be that way!'
    elif shouting(arg):
        response = 'Whoa, chill out!'
    elif question(arg):
        response = 'Sure.'
    return response
