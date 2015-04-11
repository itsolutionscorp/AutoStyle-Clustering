STATEMENT = "Whatever."
QUESTION = "Sure."
EXCLAMATION = "Whoa, chill out!"
SILENCE = "Fine. Be that way!"

def hey(message):
    if message.isupper():
        return EXCLAMATION
    if message.endswith('?'):
        return QUESTION
    if not message or message.isspace():
        return SILENCE
    return STATEMENT
