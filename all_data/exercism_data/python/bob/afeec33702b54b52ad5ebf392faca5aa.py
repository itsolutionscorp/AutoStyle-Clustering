def hey(speech):
    if speech == '':
        return 'Fine. Be that way!'
    elif speech.endswith('?'):
        return 'Sure.'
    elif speech[:-1] == speech[:-1].upper():
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
