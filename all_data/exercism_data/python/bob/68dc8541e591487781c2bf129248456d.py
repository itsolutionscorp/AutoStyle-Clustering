def hey(speech):
    if speech.strip() == '':
        return 'Fine. Be that way!'
    elif speech.isupper():
        return 'Whoa, chill out!'
    elif speech.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
