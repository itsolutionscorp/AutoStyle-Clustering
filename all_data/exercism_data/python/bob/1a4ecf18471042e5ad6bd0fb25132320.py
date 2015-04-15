# Skeleton file for the Python "Bob" exercise.

def hey(what):
    """Return reply (unistring code) from Bob the teenager"""

    quote = what.strip()

    if quote.isupper():
        return u"Whoa, chill out!"
    elif quote.endswith("?"):
        return u"Sure."
    elif quote == "":
        return u"Fine. Be that way!"
    else:
        return u"Whatever."
