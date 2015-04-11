def hey(sentence):
    sentence = sentence.strip()
    if not sentence:
        return "Fine. Be that way!"
    elif sentence.isupper():
        return "Whoa, chill out!"
    elif sentence.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
