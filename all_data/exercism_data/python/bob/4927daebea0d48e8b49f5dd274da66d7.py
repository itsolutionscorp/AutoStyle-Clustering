#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    QUESTION_REPLY = "Sure."
    YELL_REPLY = "Whoa, chill out!"
    SILENCE_REPLY = "Fine. Be that way!"
    ANYTHING_ELSE_REPLY = "Whatever."

    what = what.strip()
    if what.isupper():
        reply = YELL_REPLY
    elif what.endswith('?'):
        reply = QUESTION_REPLY
    elif not what or what.isspace():
        reply = SILENCE_REPLY
    else:
        reply = ANYTHING_ELSE_REPLY
    return reply
