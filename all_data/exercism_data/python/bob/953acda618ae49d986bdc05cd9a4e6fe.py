def hey(what):
    what = what.strip()

    if what == '':
        return 'Fine. Be that way!'
    elif what.upper() == what and TextFound(what):
        return 'Whoa, chill out!'
    elif what[len(what)-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'

def TextFound(text):
    for char in text:
        if ord(char) >= 65:
            return True
    return False

