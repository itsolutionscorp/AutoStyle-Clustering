# First, we remove the leading and trailing whitespace from input.
# Then, test if the response is empty first. Test if it is all caps
# next so an all caps question won't evaluate as a question. Test
# if it's a question, and then return "Whatever." for anything else.


def hey(what):
    """ Bob replies "Sure." if you ask him a question, "Whoa, chil
        out!" if you speak in all caps, "Fine, be that way!" if you
        say nothing, and "Whatever." if you say anything else.
    """

    what = what.strip()

    if not what:
        return "Fine. Be that way!"
    elif what.isupper():
        return "Whoa, chill out!"
    elif what[-1] == "?":
        return "Sure."
    else:
        return "Whatever."
