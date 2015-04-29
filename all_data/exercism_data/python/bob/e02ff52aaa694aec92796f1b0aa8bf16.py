# bob.py
# exercism.io: Python Exercise #1


def hey(statement):
    """Return Bob's response based on the statement addressed to him."""

    # strip statement of all whitespace
    statement = statement.strip()

    # determine if the person did not actually say anything
    if not statement:
        return "Fine. Be that way!"

    # determine if the person is yelling (all caps or symbols)
    if statement.isupper():
        return "Whoa, chill out!"

    # determine if the person asked a question (statement ends in "?")
    if statement[-1] == "?":
        return "Sure."

    # otherwise, return "Whatever." for any other response
    return "Whatever."
