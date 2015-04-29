def hey(text):
    if len(text) == 0 or text.isspace():
        return 'Fine. Be that way!'
    elif text.isupper():
        return 'Whoa, chill out!'
    elif text[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
