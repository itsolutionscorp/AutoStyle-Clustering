#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    length = len(what)
    if what[length] == "?":
        print("sure")
    elif what[length] == "!":
        print("Whoa, chill out!")
    elif what[length] == ".":
        print("Fine. Be that way!")
    else:
        print("Whatever.")

    return
