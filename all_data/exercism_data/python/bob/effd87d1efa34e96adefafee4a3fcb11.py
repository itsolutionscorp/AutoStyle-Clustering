import string

# Dictionary of different possible responses maped to their conditions
RESPONSES = {
    "default":"Whatever.",
    "question": "Sure.",
    "yelling": "Woah, chill out!",
    "nothing": "Fine. Be that way!"
}

def hey(statement):
    """
    Pass in a statement as a String, and get a response.
    """
    if isNothing(statement):
        return RESPONSES["nothing"]
    if isYelling(statement):
        return RESPONSES["yelling"]
    if isQuestion(statement):
        return RESPONSES["question"]
    else:
        return RESPONSES["default"]

def isQuestion(statement):
    """
    Returns True if the statement is a question. Defined as ending with a '?'
    """
    return statement.endswith('?')

def isYelling(statement):
    """
    Returns True if the statement is a yelling statement. "Yelling" is defined
    as a statement in which every character is in its upper case state, and it
    contains at last one character that could be lowercase but isn't.
    """
    hasUpper = False
    for char in statement:
        if char.upper() != char:
            return False
        if not hasUpper and char.lower() != char:
            hasUpper = True
    return hasUpper 

def isNothing(statement):
    return not statement.strip()
