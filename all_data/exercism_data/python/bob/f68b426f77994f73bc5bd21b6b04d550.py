#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper():
        answer = "Whoa, chill out!"
    elif what.endswith("?"):
        answer = "Sure."
    elif not what.strip():
        answer = "Fine. Be that way!"
    else:
        answer = "Whatever."
    return answer
