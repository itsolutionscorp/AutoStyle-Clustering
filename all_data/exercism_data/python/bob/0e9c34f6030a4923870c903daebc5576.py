import string

# Dictionary of different possible responses mapped to their conditions
RESPONSES = {
    "default":"Whatever.",
    "question": "Sure.",
    "yelling": "Woah, chill out!",
    "nothing": "Fine. Be that way!"
}

def hey(statement):
    if isNothing(statement):
        return RESPONSES["nothing"]
    elif isYelling(statement):
        return RESPONSES["yelling"]
    elif isQuestion(statement):
        return RESPONSES["question"]
    else:
        return RESPONSES["default"]

def isQuestion(statement):
    return statement.endswith('?')

def isYelling(statement):
    return statement.isupper()

def isNothing(statement):
    return not statement.strip()
