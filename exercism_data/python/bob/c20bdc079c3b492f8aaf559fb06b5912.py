# -*- coding: utf-8 -*-

def hey(prompt):
    # Uses the built in strip function to remove whitespace
    # characters from the edges
    prompt = prompt.strip()

    # This is first because if the string is empty
    # nothing else matters
    if (checkForEmpty(prompt)):
        return 'Fine. Be that way!'

    # This needs to be ahead of checkForStatement()
    # as not all yells necessarily end in '!' or '?'
    elif (checkForYell(prompt)):
        return 'Whoa, chill out!'

    # We have to check this after we check for a yell
    # as a yelling question is still a yell
    elif (prompt.endswith('?')):
            return 'Sure.'

    # In case it is nothing above, it must be a general statement
    else:
        return 'Whatever.'

# Checks if the prompt passed in contains
# any characters
def checkForEmpty(prompt):
    for letter in prompt:
        return False
    return True

# Checks to see if the prompt is not a question
def checkForStatement(prompt):
    if ('?' not in prompt and '!' not in prompt):
        return True
    return False

def checkForYell(prompt):
    containsLetter = False
    for letter in prompt:
        # If a string has a lowercase letter,
        # we know he isn't being yelled at
        if letter.isalpha():
            containsLetter = True
            if letter.islower():
                return False
    # We check if the string contained a letter to ensure
    # that number-only strings are not incorrectly detected
    if containsLetter:
        return True
    return False

