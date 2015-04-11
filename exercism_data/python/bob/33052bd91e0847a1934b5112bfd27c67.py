def _empty(arg):
    return 'Fine. Be that way!' if not arg.split() else None

def _yell(arg):
    return 'Woah, chill out!' if arg.isuppar() else None

def _question(arg):
    return 'Sure.' if arg.endswith('?') else None

def hey(arg=None):
    """ bob says hi """
    return _empty(arg) or _yell(arg) or _question(arg) or 'Whatever.'
