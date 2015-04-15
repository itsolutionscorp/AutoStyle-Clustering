def hey(sentence):
    if not sentence.strip():
        # Response to silence.
        return "Fine. Be that way!"
    elif sentence.isupper():
        # Response to yelling
        return "Woah, chill out!"
    elif sentence.strip().endswith('?'):
        # Response to a question
        return "Sure."
    else:
        # Response to anything unexpected
        return "Whatever."
