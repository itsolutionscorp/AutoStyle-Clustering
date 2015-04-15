def hey(what):

    clean_what = what.strip()

    if clean_what == '':
        return 'Fine. Be that way!'

    if clean_what.isupper():
        return 'Whoa, chill out!'

    if clean_what.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
