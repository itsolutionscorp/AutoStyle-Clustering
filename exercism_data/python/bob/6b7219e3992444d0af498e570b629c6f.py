def hey(str_in):
    """
    Bob the lackadaisical teenager.
    """

    # get rid of whitespace and "empty" characters
    str_in = str_in.strip()
    if str_in.endswith('?'):
        # '?' on it's own is not upper
        # isupper is true if all letter chars are upper
        if str_in.isupper():
            return 'Whoa, chill out!'
        else:
            return 'Sure.'
    # this check is after empty chars are gone
    elif len(str_in) == 0:
        return 'Fine. Be that way!'
    elif str_in.isupper():
        return 'Whoa, chill out!'
    else:
        return u'Whatever.'
