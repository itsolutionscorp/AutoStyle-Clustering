import regex as re

# Accept a message and return a response
def hey(message):
    response = ''
    if (isEmpty(message)):
        response = 'Fine. Be that way!'
    elif (isYelling(message)):
        response = 'Woah, chill out!'
    elif (isQuestion(message)):
        response = 'Sure.'
    else:
        response = 'Whatever.'
    #print '\n' + response
    return response

# Check if message is empty
def isEmpty(message):
    isEmpty = False
    if not message:
        isEmpty = True
    elif (re.match(u'[\x00-\x20]', message)):
        if (re.search(u'[A-Za-z0-9]', message)):
            isEmpty = False
        else:
            isEmpty = True
    return isEmpty

# Check if message is yelling
def isYelling(message):
    isYelling = False
    if (re.search(u'[A-Z]', message)):
        if not (re.search(u'[a-z]', message)):
            if (re.compile(u'\p{Ll}', re.UNICODE).search(message)):
                ifYelling = False
            else:
                isYelling = True

    return isYelling

# Check if message is a question
def isQuestion(message):
    return message.endswith('?')

def main(message):
    print hey(message)

if __name__ == '__main__':
    main('         hmmmmmmm...')
