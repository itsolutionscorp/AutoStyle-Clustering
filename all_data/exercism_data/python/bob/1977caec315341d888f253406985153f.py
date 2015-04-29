def hey(what):
    if what == '' or what.isspace():
        return "Fine. Be that way!"
    elif what.isupper():
        return "Whoa, chill out!"
    elif what.strip().endswith('?'):
        return "Sure."
    else:
        return 'Whatever.'
