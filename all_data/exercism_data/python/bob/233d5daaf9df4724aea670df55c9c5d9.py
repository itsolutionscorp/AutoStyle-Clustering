#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    s = what
    # empty string/ special characters
    if s.isspace() or not s:
        return "Fine. Be that way!"
    else:
        if s.isupper() and (s.endswith("?") or s.endswith("!") or s.endswith("")):
            return "Woah, chill out!"
        else:
            if s.endswith("?"):
                return "Sure."
            else:
                if s.endswith(".") or not s.isupper():
                    return "Whatever."
