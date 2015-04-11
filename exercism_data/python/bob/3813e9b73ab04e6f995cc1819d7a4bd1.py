def hey(speech):
    if speech == speech.upper() and speech.lower() != speech.upper():
        return "Woah, chill out!"
    if speech.endswith("?"):
        return "Sure."
    if speech.strip():
        return "Whatever."
    return "Fine. Be that way!"
