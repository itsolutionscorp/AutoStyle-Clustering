def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    elif what.strip() == '':
        return 'Fine. Be that way!'
    elif (what[-1] == '?' or what.endswith(' ') ):
        return 'Sure.'
    else:
        return 'Whatever.'
