"""Bob is a lackadaisical teenager."""

def hey(msg=''):
    """Say something to Bob. Bob will reply by returning a string."""

    msg.encode('ascii', 'ignore')
    if not msg or msg == '' or msg.isspace():
        return 'Fine. Be that way!'
    elif msg.isupper():
        return "Whoa, chill out!"
    elif msg.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
