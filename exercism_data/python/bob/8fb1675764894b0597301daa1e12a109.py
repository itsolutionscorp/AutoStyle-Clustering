def hey(prompt):
    if prompt.strip() == "":
        return "Fine. Be that way!"
    if prompt.isupper():
        return "Woah, chill out!"
    if prompt.endswith("?"):
        return "Sure."
    return "Whatever."
