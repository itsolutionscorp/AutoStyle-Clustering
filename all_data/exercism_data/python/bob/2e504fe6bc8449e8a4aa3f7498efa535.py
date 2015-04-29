def hey(what):

    what = what.strip()

    is_question = what and what[-1] == '?'
    is_silence = not what
    is_all_caps = what.isupper()

    if (is_silence):
        return 'Fine. Be that way!'

    elif (is_all_caps):
        return 'Whoa, chill out!'

    elif (is_question):
        return 'Sure.'

    else:
        return 'Whatever.'
