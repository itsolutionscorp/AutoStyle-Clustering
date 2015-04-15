def hey(what):
    what = what.strip()

    if what == '':
        return 'Fine. Be that way!'
    if what.isupper() \
       and reduce(lambda x, y: x or y.isalpha(), what, False):
        return 'Whoa, chill out!'
    if what[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
