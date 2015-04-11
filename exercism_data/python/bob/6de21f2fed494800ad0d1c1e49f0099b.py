def hey(what):
    what = what.strip()
    if what == what.upper() != what.lower():
        return 'Whoa, chill out!'
    elif not len(what):
        return 'Fine. Be that way!'
    elif what[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
