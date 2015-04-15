def hey(input_text):
    if (len(input_text) <= 0) or (input_text.isspace()):
        return u'Fine. Be that way!'
    if input_text.isupper():
        return u'Whoa, chill out!'
    elif input_text[-1] == '?':
        return u'Sure.'
    else:
        return u'Whatever.'
