#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # Instructions don't say what to do if you ask bob a question and yell at him.
    if not what.strip():
        return "Fine. Be that way!"
    if what.strip().endswith('?') and not what.isupper():
        return "Sure."
    elif what.isupper():
        return "Whoa, chill out!"
    else:
        return "Whatever."
