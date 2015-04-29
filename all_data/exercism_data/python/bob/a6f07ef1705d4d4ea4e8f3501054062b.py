#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    """Return a response for 'Bob' given a line of input.

    Return "Fine. Be that way!" if nothing was said (i.e what is empty)
    Return "Whoa, chill out!" if what invovles yelling.
    Return "Sure." if what contains a question.
    Return "Whatever." otherwise.

    Question means a question mark is present.
    Yelling means upper case letters and not that an explanation mark was used.

    Yelling takes precedences over a question.
    """

    if not what or not what.strip():
        # The person didn't say anything.
        return 'Fine. Be that way!'

    classification = [c.isupper() for c in what if c.isalpha()]
    if classification and all(classification):
        # The person yelled.
        return 'Whoa, chill out!'

    if what.endswith('?'):
        # the person asked a question.
        return 'Sure.'

    return 'Whatever.'
