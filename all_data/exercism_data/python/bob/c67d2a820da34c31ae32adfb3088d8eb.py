def is_yelling(what):
    words = filter(lambda x: x.isalpha() or x == ' ', what).split()
    yells = filter(unicode.isupper, words)
    return len(yells) >= len(words) and words != []

def hey(what):
    what = what.strip()

    if what == '':
        return 'Fine. Be that way!'
    elif is_yelling(what):
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
