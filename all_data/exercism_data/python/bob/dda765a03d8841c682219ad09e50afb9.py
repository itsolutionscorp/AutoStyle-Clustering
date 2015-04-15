def hey(speech):
    if yelling(speech):
        return 'Whoa, chill out!'
    elif asking_question(speech):
        return 'Sure.'
    elif silence(speech):
        return 'Fine. Be that way!'
    return 'Whatever.'


def yelling(speech):
    return speech.isupper()


def asking_question(speech):
    return speech.endswith('?')


def silence(speech):
    return not speech.strip()
