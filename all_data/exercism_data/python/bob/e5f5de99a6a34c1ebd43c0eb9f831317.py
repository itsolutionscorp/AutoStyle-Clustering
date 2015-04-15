
def hey(msg):
    # ends with ? and is not uppercase
    if msg.endswith('?') and not msg.isupper():
        answer = 'Sure.'
    # is uppercase
    elif msg.isupper():
        answer = 'Whoa, chill out!'
    # not empty or is spaced
    elif not msg or msg.isspace():
        answer = 'Fine. Be that way!'
    # everything else
    else:
        answer = 'Whatever.'

    # return an answer
    return answer
