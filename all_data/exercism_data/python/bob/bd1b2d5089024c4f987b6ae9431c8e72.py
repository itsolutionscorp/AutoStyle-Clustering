def hey(what):
    """
    :type what: str
    :rtype : str
    """
    what = what.strip()
    if not what:
        return 'Fine. Be that way!'
    if what.isupper():
        return 'Whoa, chill out!'
    if what.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
