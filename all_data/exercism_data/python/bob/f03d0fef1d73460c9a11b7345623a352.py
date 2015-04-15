def hey(what):
    what = what.strip()
    if what == '':
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what[len(what) - 1] == '!':
        return 'Whatever.'
    elif what[len(what) - 1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
