import re
com = ['wink', 'double blink', 'close your eyes', 'jump']

def handshake(code):
    result = []
    if isinstance(code, basestring):
        if not re.match(r'^[01]+$', code):
            raise Exception('value error')
    else:
        if isinstance(code, int):
            if code < 0:
                raise Exception('value error')
            code = str(bin(code)[2:])

    for index, val in enumerate(reversed(code)):
        if val == '1' and index < 4:
            result.append(com[index])
    return result

def code():
    pass


#print handshake(9)
#print handshake('10110')
#print handshake('101')
#
