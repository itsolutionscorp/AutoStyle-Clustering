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

    if is_yell(message):
        return ANSWER_FOR_YELL

    if is_question(message):
        return ANSWER_FOR_QUESTION

    if is_silent(message):
        return ANSWER_FOR_SILENCE

    return ANSWER_FOR_ANYTHING_ELSE

# Check if the message is a question.
# A question is a message that ends with a question mark '?'
def is_question(message):

    return message.endswith('?')

# Check if the message is a yell.
# A yell is a message that is written using only upper case letters
def is_yell(message):

    # Check if the message contains unicode characters
    if isinstance(message, unicode):
        # Properly format the message so that isupper() doesn't return false positives when given unicode characters
        message = message.encode('utf-8').decode('utf-8')

    return message.isupper()

# Check if the message is empty (i.e. silent)
def is_silent(message):

    # Remove any whitespace characters first, they're irrelevant.
    return not message.strip()
