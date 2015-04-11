def hey(what):
    say = what.strip()
    if say.isupper():
        return 'Whoa, chill out!'
    elif say.endswith('?'):
        return 'Sure.'
    elif not say:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
