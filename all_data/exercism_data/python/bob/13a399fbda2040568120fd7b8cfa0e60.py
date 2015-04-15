#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    phrase = what.strip()
    question = phrase.endswith("?")
    silence = not phrase
    shouting = phrase.isupper()
    
    if shouting:
        return "Whoa, chill out!"
    elif silence:
        return "Fine. Be that way!"
    elif question:
        return "Sure."
    return "Whatever."
