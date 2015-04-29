#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    """
    Simulate the responses of Bob, the angsty teenager.
    """

    # Determine type of prompt.
    question = what.strip().endswith('?')
    yelling = what.strip().isupper()
    nothing = not what.strip()

    # Flow control guarantees that if a question is yelled it will still be
    # treated as yelling.
    if yelling:
        return "Whoa, chill out!"
    elif question:
        return "Sure."
    elif nothing:
        return "Fine. Be that way!"
    else:
        return "Whatever."
