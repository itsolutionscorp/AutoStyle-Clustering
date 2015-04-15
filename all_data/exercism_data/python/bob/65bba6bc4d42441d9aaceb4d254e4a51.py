def hey(what):
    return_phrase = 'Whatever.'
    what = what.strip()

    if what == '':
        return_phrase = 'Fine. Be that way!'

    elif what.isupper():
        return_phrase = 'Woah, chill out!'

    elif what.endswith('?'):
        return_phrase = 'Sure.'

    return return_phrase
