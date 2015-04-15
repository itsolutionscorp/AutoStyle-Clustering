#
# Skeleton file for the Python "Bob" exercise.
#

#
#Here I set up some functions to make the
#   code more readable.
def isLetter(string):
    return string.isalpha()

def isQuestion(string):
    return string[-1] == '?'

#
#Below follows the function to pass through bob_test.py
#
def hey(conversation):
    if conversation.strip(' \t\n\r') == '':
        return 'Fine. Be that way!'
    elif conversation.upper() == conversation:
        if not any(isLetter(piece) for piece in conversation):
            if isQuestion(conversation):
                return 'Sure.'
            else: return 'Whatever.' #In the case of numbers with no letters
        else: return 'Whoa, chill out!'
    elif isQuestion(conversation):
        return 'Sure.'  
    else:  return 'Whatever.'
