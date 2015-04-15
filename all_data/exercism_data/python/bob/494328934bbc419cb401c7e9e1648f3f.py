def hey(sentence):
    if sentence.strip() == "":
        # Response to silence.
        return "Fine. Be that way!"
    elif sentence.isupper():
        # Response to yelling
        return "Woah, chill out!"
    elif sentence[-1] == '?':
        # Response to a question
        return "Sure."
    # Response to anything unexpected
    return "Whatever."
