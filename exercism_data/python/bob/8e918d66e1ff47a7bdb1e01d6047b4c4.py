#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what=what.strip()
    if (what.isupper()): return "Whoa, chill out!"
    if (what.endswith("?")): return "Sure."
    if (any(ord(x) > 32 for x in what)):   return "Whatever."
    return "Fine. Be that way!"
