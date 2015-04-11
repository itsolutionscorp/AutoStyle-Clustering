def hey(what):
    QUESTION_RESPONSE = 'Sure.'
    YELL_RESPONSE = 'Whoa, chill out!'
    SILENCE_RESPONSE = 'Fine. Be that way!'
    WHATEVER_RESPONSE = 'Whatever.'

    text = what.strip()

    if text.isupper():
        return YELL_RESPONSE
    elif text.endswith('?'):
        return QUESTION_RESPONSE 
    elif text== '':
        return SILENCE_RESPONSE
    else:
        return WHATEVER_RESPONSE
