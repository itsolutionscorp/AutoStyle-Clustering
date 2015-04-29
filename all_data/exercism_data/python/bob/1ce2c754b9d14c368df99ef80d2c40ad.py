statement = "Whatever."
question = "Sure."
exclamation = "Whoa, chill out!"
silence = "Fine. Be that way!"

def hey(message):
    if message.isupper():
        return exclamation
    if message.endswith('?'):
        return question
    if not message or message.isspace():
        return silence
    return statement
