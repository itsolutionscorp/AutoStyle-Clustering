#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    '''say hey to Bob. he's a tennager though'''

    if what.rstrip() == "":
        # empty string or nothing was passed
        # Bob doesn't care for that
        return "Fine. Be that way!"

    if what.isupper():
        # upper case is yelling. So rude!
        return "Whoa, chill out!"

    if what.rstrip().endswith("?"):
        # are you expecting an answer from Bob?
        # Bob has ready-made teenager response.
        return "Sure."

    # Bob's default teenager mode.
    return "Whatever."
