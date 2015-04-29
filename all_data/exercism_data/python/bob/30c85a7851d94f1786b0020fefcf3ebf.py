#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    return [x for x in [
        "Whoa, chill out!" if what.isupper() else 0,
        "Sure." if what.strip().endswith("?") else 0,
        "Fine. Be that way!" if not what.strip() else 0,
        "Whatever."
    ] if x][0]
