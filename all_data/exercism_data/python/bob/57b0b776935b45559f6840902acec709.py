def __is_question(phrase):
    return phrase.endswith("?")


def __is_yelling(phrase):
    return phrase.isupper()


def hey(phrase):
    phrase = phrase.strip()
    if not phrase:
        return "Fine. Be that way!"
    elif __is_yelling(phrase):
        return "Woah, chill out!"
    elif __is_question(phrase):
        return "Sure."
    else:
        return "Whatever."
