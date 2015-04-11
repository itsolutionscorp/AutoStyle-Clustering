def hey(what):

    if (what.endswith('?') or what.endswith(' ') ) and not what.isupper():
        return 'Sure.'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what.strip() == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
