def hey(what):
    bob = ''
    what = what.strip()
    if what == '' or what[len(what) - 1] == '\t':
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what[len(what) - 1] == '!':
        return 'Whatever.'
    elif what[len(what) - 1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
