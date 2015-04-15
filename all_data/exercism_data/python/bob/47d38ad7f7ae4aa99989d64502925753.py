def hey(prompt):
    if prompt.isupper():
        return "Whoa, chill out!"
    elif prompt.endswith("?"):
        return "Sure."
    elif len(prompt.strip()) == 0:
        return "Fine. Be that way!"
    else:
        return "Whatever."
