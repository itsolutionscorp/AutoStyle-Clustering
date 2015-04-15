def hey(what):
    what_trimmed = what.strip()
    if not what_trimmed:
        return u'Fine. Be that way!'
    elif what_trimmed.isupper():
        return u'Whoa, chill out!'
    elif what_trimmed.endswith('?'):
        return u'Sure.'
    return u'Whatever.'
