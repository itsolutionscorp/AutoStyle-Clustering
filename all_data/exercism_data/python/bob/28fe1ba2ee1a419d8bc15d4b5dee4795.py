def hey(sentence):
    if sentence.isupper() == True:
        return "Whoa, chill out!"
    elif sentence.endswith("?"):
        return "Sure."
    elif "" == sentence or "    \t" == sentence:
        return "Fine. Be that way!"
    else:
        return "Whatever."
