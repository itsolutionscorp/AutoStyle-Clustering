# protomech

def hey(msg):

    isBlank = len(msg) == 0 or msg.isspace()
    isUppercase = msg.isupper()
    isQuestion = not isBlank and msg[-1] == '?' 

    if isUppercase:
        return 'Whoa, chill out!'
    if isQuestion:
        return 'Sure.'
    if isBlank:
        return 'Fine. Be that way!'

    # default answer
    return 'Whatever.'
