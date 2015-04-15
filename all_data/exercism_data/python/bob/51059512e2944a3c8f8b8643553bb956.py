SILENCE, EXCLAMATION, QUESTION, STATEMENT = xrange(4)

RESPONSES = {
    SILENCE: 'Fine. Be that way!',
    EXCLAMATION: 'Whoa, chill out!',
    QUESTION: 'Sure.',
    STATEMENT: 'Whatever.',
}

def angry(statement):
    uppercase = [c.isupper() for c in statement if c.isalpha()]
    return len(uppercase) != 0 and all(uppercase)

def hey(statement):
    if len(statement.strip()) == 0:
        return RESPONSES[SILENCE]
    elif angry(statement):
        return RESPONSES[EXCLAMATION]
    elif statement.endswith('?'):
        return RESPONSES[QUESTION]

    return RESPONSES[STATEMENT]
