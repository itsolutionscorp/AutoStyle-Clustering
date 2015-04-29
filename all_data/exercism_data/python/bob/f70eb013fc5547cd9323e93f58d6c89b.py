#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):

    if what[-1] == "?":
        return "Sure."

    if uppercase(what):
        return "Whoa, chill out!"

    if not what or what == "":
        return "Fine. Be that way!"

    else:
        return "Whatever."

def uppercase(text):
    words = text.split()
    for word in words:

        # if all characters in a word are uppercase
        if all(char.isupper() for char in word):
            return True

    else:
        return False
