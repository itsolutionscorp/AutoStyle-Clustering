def is_silent(inpt):
    return len(inpt) < 1


def is_yelling(inpt):
    return inpt.isupper()


def is_question(inpt):
    return inpt.endswith('?')


def hey(inpt):
    inpt = inpt.strip()

    if is_silent(inpt):
        return 'Fine. Be that way!'

    if is_yelling(inpt):
        return 'Woah, chill out!'

    if is_question(inpt):
        return "Sure."

    return 'Whatever.'
