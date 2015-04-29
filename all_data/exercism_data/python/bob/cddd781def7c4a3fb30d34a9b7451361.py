def hey(sentence):
    if sentence == sentence.upper() and sentence != sentence.lower():
        return "Whoa, chill out!"
    elif sentence.strip() == '':
        return "Fine. Be that way!"
    elif sentence[len(sentence)-1] == '?':
        return "Sure."
    else:
        return "Whatever."
