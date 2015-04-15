def hey(speech):
    speech = speech.strip()
    if len(speech) == 0:
        return 'Fine. Be that way!'
    elif speech.isupper():
        return 'Whoa, chill out!'
    elif speech[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
