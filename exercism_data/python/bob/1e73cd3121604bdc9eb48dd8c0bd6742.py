def hey(what):
    ''' Returns Bob's response '''

    what = what.strip()

    if (what.upper() == what) and [x for x in what if x.isalpha()]:
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    elif not what:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
