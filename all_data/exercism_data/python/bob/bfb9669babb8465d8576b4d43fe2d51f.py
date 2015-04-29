def hey(sentence):
    sentence = sentence.strip()
    if sentence:
        if sentence.isupper():
            return "Whoa, chill out!"
        elif sentence[-1] == '?':
            return "Sure."
        else:
            return "Whatever."
    else:
        return "Fine. Be that way!"
