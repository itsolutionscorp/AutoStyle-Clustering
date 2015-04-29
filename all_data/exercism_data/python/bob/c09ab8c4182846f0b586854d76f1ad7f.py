def hey(text):
	text = text.strip()
	
    if text == "":
        return "Fine. Be that way!"

    if text.isupper():
        return "Whoa, chill out!"

    if text.endswith("?"):
        return "Sure."

    return "Whatever."
