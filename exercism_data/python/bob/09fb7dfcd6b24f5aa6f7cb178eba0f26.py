def hey(arg=None):
    """ bob says hi """
    def _empty():
        return 'Fine. Be that way!' if not arg.split() else None

    def _yell():
        return 'Whoa, chill out!' if arg.isupper() else None

    def _question():
        return 'Sure.' if arg.endswith('?') else None

    return _empty() or _yell() or _question() or 'Whatever.'
