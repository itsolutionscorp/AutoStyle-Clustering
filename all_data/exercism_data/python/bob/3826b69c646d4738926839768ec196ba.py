#
# Skeleton file for the Python "Bob" exercise.
#

question_words = ["what", "why", "when", "how"]
hell_words  = "hell, "

def is_question(what):
    if what and "?" == what[-1]:
	return "Sure."
    return False

def is_yell(what):
    if what.isupper():
        return "Whoa, chill out!"
    return False

def is_address_without_saying(what):
    if not what:
        return "Fine. Be that way!"
    return False

def is_anything_else(what):
    return "Whatever."


def hey(what):
    what = what.strip()
    answer = is_yell(what)
    if answer: return answer
    answer = is_question(what)
    if answer: return answer
    answer = is_address_without_saying(what)
    if answer: return answer
    answer = is_anything_else(what)
    return answer
