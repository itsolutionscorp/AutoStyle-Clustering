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
    # 'prompt' evaluates to False if the string
    # doesn't contain any characters
    if prompt:
        return False
    return True

def checkForYell(prompt):
    # isupper() returns True in the case that all characters
    # are cased, and there exists one cased character
    # False otherwise
    return prompt.isupper()

