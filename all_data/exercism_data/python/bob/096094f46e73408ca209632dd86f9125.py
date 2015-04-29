def hey(sentence):
    sentence = sentence.strip()
    if sentence:
        if sentence.isupper():
            return "Whoa, chill out!"
        elif sentence.endswith('?'):
            return "Sure."
        else:
            return "Whatever."
    else:
        return "Fine. Be that way!"
