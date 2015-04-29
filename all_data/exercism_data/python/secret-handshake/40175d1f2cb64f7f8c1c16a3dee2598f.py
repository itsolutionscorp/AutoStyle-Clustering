import re

binre = re.compile('^[0-1]+$')
moves = {1: 'wink', 10: 'double blink', 100: 'close your eyes', 1000: 'jump', 'wink': 1, 'double blink': 10, 'close your eyes': 100, 'jump': 1000}

def handshake(code):
    hand_out = []
    if isinstance(code,int):
        code = '{0:b}'.format(code)
    if binre.match(code) == None:
        return []
    code = list(code)
    cur = 0
    while code:
        curnum = code.pop()
        if curnum == '1' and cur < 4:
            hand_out.append(moves[10**cur])
        elif curnum =='1':
            hand_out = hand_out[::-1]
        cur += 1
    return hand_out

def code(handshake):
    code_out = ['0']*5
    if not handshake:
        return '0'
    
    try:
        binhand = [moves[move] for move in handshake]
    except KeyError:
        return '0'

    if sorted(binhand) == binhand[::-1] and len(binhand) != 1:
        code_out[0] = '1'
    elif sorted(binhand) != binhand:
        return '0'

    for i in binhand:
        code_out[5-len(str(i))] = '1'

    while code_out[0] == '0':
        code_out.pop(0)

    return ''.join(code_out)
