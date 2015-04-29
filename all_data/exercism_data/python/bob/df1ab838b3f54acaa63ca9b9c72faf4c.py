def hey(what):
    phrase = what.strip()
    if phrase:
        if phrase.isupper():
            return u'Whoa, chill out!'
        elif phrase[-1] == r'?':
            return u'Sure.'
        else:
            return u'Whatever.'
    else:
        return u'Fine. Be that way!'
