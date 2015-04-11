#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if checkYelling(what):
        response = 'Whoa, chill out!'
    elif checkQuestion(what):
        response = 'Sure.'
    elif checkSilence(what):
        response = 'Fine. Be that way!'
    else:
        response = 'Whatever.'
    return response

def checkQuestion(speech):
    question = False
    if speech.strip().endswith('?'):
        question = True
    return question

def checkSilence(speech):
    silence = False
    if not speech or speech.isspace():
        silence = True
    return silence

def checkYelling(speech):
    yelling = False
    if speech.isupper():
        yelling = True
    return yelling
