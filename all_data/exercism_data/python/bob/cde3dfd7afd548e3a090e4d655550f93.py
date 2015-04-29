def hey(text):
    """
    Simple function which passes tests ran from bob_test.py.
    """
    if text.isupper():
        return "Woah, chill out!"
    elif text[-1:] == "?":
        return "Sure."
    elif text.isspace() or len(text) == 0:
        return "Fine. Be that way!"
    else:
        return "Whatever."
