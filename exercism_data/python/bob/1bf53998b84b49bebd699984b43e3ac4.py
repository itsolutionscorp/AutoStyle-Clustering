def hey(what):
    if not what or what.isspace():
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what.strip()[-1] == "?":
        return 'Sure.'
    else:
        return 'Whatever.'
