"""
    Meet Bob
"""

def hey(msg):
    """ Bob has conversations. """

    # remove whitespace
    msg = msg.strip()

    # address bob w/o saying anything
    if msg == '':
        return 'Fine. Be that way!'
    # if you yell at him
    elif msg.isupper():
        return 'Whoa, chill out!'
    # if you ask him a question
    elif msg[-1] == '?':
        return 'Sure.'
    # whatever to everything else
    else:
        return 'Whatever.'
