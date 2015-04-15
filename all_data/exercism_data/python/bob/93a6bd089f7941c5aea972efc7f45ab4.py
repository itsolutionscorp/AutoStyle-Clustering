def hey(str_in):
    """
    Bob the lackadaisical teenager.
    """

    # get rid of whitespace and "empty" characters
    str_in = str_in.strip()
    # this check is after empty chars are gone
    if len(str_in) == 0:
        return 'Fine. Be that way!'
    # isupper is true if all letter chars are upper
    elif str_in.isupper():
        return 'Whoa, chill out!'
    elif str_in.endswith('?'):
        return 'Sure.'
    else:
        return u'Whatever.'
