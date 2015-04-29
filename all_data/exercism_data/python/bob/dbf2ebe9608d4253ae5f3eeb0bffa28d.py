def hey(speech):
    if speech.isupper():
        return "Whoa, chill out!"
    elif speech.endswith("?"):
        return "Sure."
    elif speech.strip() == "":
        return "Fine. Be that way!"
    else:
        return "Whatever."
