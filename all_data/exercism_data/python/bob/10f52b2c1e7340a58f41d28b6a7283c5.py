import string

def hey(sentence):
    status=False
    for i in sentence:
        if i in string.letters:
            status=True
            break
    if sentence.upper() == sentence and status:
        return "Whoa, chill out!"
    elif sentence.isspace() or len(sentence) == 0:
        return "Fine. Be that way!"
    elif sentence.find("?") == len(sentence) - 1:
        return "Sure."
    else:
        return "Whatever."
