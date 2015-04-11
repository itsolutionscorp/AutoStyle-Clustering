#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    QUESTION_ANSWER = "Sure."
    YELL_ANSWER = "Whoa, chill out!"
    EMPTY_ANSWER = "Fine. Be that way!"
    DEFAULT_ANSWER = "Whatever."

    #Bob doesn't care about empty space
    what_no_whitespace = what.strip()

    if not what_no_whitespace:
        return EMPTY_ANSWER

    #To be a yell any words must be in UPPERCASE
    lower_letters = [c for c in what_no_whitespace if c.islower()]
    upper_letters = [c for c in what_no_whitespace if c.isupper()]
    if len(lower_letters) == 0 and len(upper_letters) > 0:
        return YELL_ANSWER

    if "?" == what_no_whitespace[-1]:
        return QUESTION_ANSWER

    return DEFAULT_ANSWER
