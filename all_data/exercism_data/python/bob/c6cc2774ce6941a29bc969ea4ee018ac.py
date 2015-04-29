def hey(content):
    """
    Simple function which passes tests ran from bob_test.py.
    """
    text = content.strip()

    if text.isupper():
        return "Whoa, chill out!"
    elif text.endswith("?"):
        return "Sure."
    elif not text:
        return "Fine. Be that way!"
    else:
        return "Whatever."
