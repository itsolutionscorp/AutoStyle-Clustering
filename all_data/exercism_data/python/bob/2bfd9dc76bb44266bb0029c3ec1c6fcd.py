import re

def hey(what):

    what = what.strip()
    letters = re.compile('[a-zA-Z]')

    is_question = True if what and what[-1] == '?' else False
    is_silence = True if not what else False
    has_letters = letters.search(what)
    is_all_caps = what.upper() == what

    if (is_silence):
        return 'Fine. Be that way!'

    elif (has_letters and is_all_caps):
        return 'Whoa, chill out!'

    elif (is_question):
        return 'Sure.'

    else:
        return 'Whatever.'
