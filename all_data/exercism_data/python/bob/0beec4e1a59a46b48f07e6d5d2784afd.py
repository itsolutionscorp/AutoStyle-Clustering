def hey(input):
    if is_shouting(input):
        return "Woah, chill out!"
    elif is_question(input):
        return "Sure."
    elif is_silence(input):
        return "Fine. Be that way!"
    else:
        return "Whatever."

def is_question(input):
    return input.endswith('?')

def is_shouting(input):
    return input.isupper()

def is_silence(input):
    return input.strip() == ''
