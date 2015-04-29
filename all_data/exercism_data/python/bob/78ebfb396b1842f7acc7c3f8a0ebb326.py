# coding: utf-8
def hey(what):
    """Simulate Bob's response.
    """
    try:
        what_stripped = what.strip()

        if what_stripped == "":
            return "Fine. Be that way!"
        elif what_stripped.isupper():
            return "Whoa, chill out!"
        elif what_stripped.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
    except AttributeError:
        print("Error: Please input string object!")
        return ""
