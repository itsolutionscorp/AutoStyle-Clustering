
def hey(msg):
    # is uppercase
    if msg.isupper():
        answer = 'Whoa, chill out!'
    # ends with ?
    elif msg.endswith('?'):
        answer = 'Sure.'
    # not empty or is spaced
    elif not msg or msg.isspace():
        answer = 'Fine. Be that way!'
    # everything else
    else:
        answer = 'Whatever.'

    # return an answer
    return answer
