#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what_no_whitspace = what.strip()
    # Instructions don't say what to do if you ask bob a question and yell at him.
    if not what_no_whitspace:
        return "Fine. Be that way!"
    elif what_no_whitspace.endswith('?') and not what_no_whitspace.isupper():
        return "Sure."
    elif what_no_whitspace.isupper():
        return "Whoa, chill out!"
    else:
        return "Whatever."
