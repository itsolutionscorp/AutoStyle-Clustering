#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    # Assert that this is a string.
    assert isinstance(what, str)

    # If isallcaps is true, that means we have letters:
    # if it is false, that means that it is only numbers.
    isallcaps = what.isupper() != what.islower()
    for i in what:
        if isallcaps and not i.isupper() and i.isalpha():
            isallcaps = False

    if isallcaps:
        return "Whoa, chill out!"

    # If this ends with a question mark, this is a question.
    if what.endswith("?"):
        return "Sure."

    # If this is empty (len == 0) or is all spaces (isspace)
    # give back the proper response.
    if len(what) == 0 or what.isspace():
        return "Fine. Be that way!"

    # The default response.
    return "Whatever."
