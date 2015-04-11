def hey(sentence):
    if sentence == sentence.upper() and sentence != sentence.lower():
        return "Whoa, chill out!"
    elif sentence.strip() == '':
        return "Fine. Be that way!"
    elif sentence.endswith("?"):
        return "Sure."
    else:
        return "Whatever."
