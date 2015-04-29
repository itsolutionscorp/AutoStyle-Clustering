def hey(arg=None):
    """ bob says hi """
    def _empty():
        nonlocal arg
        return 'Fine. Be that way!' if not arg.split() else None

    def _yell():
        nonlocal arg
        return 'Woah, chill out!' if arg.isupper() else None

    def _question():
        nonlocal arg
        return 'Sure.' if arg.endswith('?') else None

    return _empty() or _yell() or _question() or 'Whatever.'
