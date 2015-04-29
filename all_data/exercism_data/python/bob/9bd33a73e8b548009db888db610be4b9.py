#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    stripped = str(what).strip()
    
    if len(stripped) == 0:
        return "Fine. Be that way!"
    if stripped.isupper():
        return "Whoa, chill out!"
    if stripped.endswith("?"):
        return "Sure."
    else:
        return "Whatever."
