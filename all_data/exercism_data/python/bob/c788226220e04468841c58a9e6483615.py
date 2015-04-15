def hey(what):
    return_phrase = 'Whatever.'
    what = what.strip()

    if what == '':
        return_phrase = 'Fine. Be that way!'

    elif is_shouting(what):
        return_phrase = 'Woah, chill out!'

    elif is_question(what):
        return_phrase = 'Sure.'

    return return_phrase


def is_question(what):
    return what[-1] == '?'


def is_shouting(what):
    return (what.upper() == what) and (what.lower() != what)
