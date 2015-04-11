# Bob gives certain replies based on what you tell him.
# This module figures out the appropriate response

# Responses
ANSWER_FOR_QUESTION = 'Sure.'
ANSWER_FOR_YELL = 'Whoa, chill out!'
ANSWER_FOR_SILENCE = 'Fine. Be that way!'
ANSWER_FOR_ANYTHING_ELSE = 'Whatever.'

# Return the proper response from Bob based on the input message
def hey(message):

    # First check if this is actually a string.
    # If it's not, then just return the same result as for 'anything else'
    if not isinstance(message, basestring):
        return ANSWER_FOR_ANYTHING_ELSE

    # Remove any whitespace characters first, they're irrelevant.
    message = message.strip()

    if is_silent(message):
        return ANSWER_FOR_SILENCE

    if is_yell(message):
        return ANSWER_FOR_YELL

    if is_question(message):
        return ANSWER_FOR_QUESTION

    return ANSWER_FOR_ANYTHING_ELSE

# Check if the message is a question.
# A question ends with a question mark '?'
def is_question(message):

    return message.endswith('?')

# Check if the message is a yell.
# A yell is written using only upper case letters
def is_yell(message):

    return message.isupper()

# Check if the message is silent
# A silent message is empty
def is_silent(message):

    return len(message) == 0
