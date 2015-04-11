#!/usr/bin/python

"""Quick solution for the Bob's problem."""
def hey(what):
    if what.strip() == "":
        return "Fine. Be that way!"
    elif what.isupper():
        return "Whoa, chill out!"
    elif what.strip()[-1] == "?":
        return "Sure."
    else:
        return "Whatever."

if __name__ == "__main__":
    """Quick Example"""
    print hey("Hello, there!")
    print hey("How have you been?")
    print hey("WAKE UP!")
    print hey("  ")
