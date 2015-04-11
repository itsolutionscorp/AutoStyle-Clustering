#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()

    all_iter = [x.isupper() for x in what if x.isalpha()]
    if len(all_iter) > 0 and all(all_iter):
        reply = "Whoa, chill out!"
    elif len(what) > 0 and what.find('?') == len(what)-1:
        reply = "Sure."
    elif what == '':
        reply = "Fine. Be that way!"
    else:
        reply = "Whatever."

    return reply
