def hey(sentence):
    if not sentence.strip().strip('.?!'):
        # Response to silence.
        # First strip: whitespace removal
        # Second strip: punctuation only, eg: "..."
        return "Fine. Be that way!"
    elif sentence.isupper():
        # Response to yelling
        return "Woah, chill out!"
    elif sentence.endswith('?'):
        # Response to a question
        return "Sure."
    else:
        # Response to anything unexpected
        return "Whatever."
