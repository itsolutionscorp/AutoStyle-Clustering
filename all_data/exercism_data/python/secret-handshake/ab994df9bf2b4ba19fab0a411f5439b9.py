ACTIONS = ['wink', 'double blink', 'close your eyes', 'jump']

def handshake(code):
    if type(code) == str:
        try: code = int(code, base=2)
        except: code = 0

    code = max(code, 0)

    actions = []
    for i in range(4):
        if code & 2 ** i:
            actions.append(ACTIONS[i])

    if code & 16:
        actions = list(reversed(actions))

    return actions


def code(actions):
    result = 0

    for action in actions:
        try:
            i = ACTIONS.index(action)
            result |= 2 ** i
        except:
            result = 0
            break

    if result != 1 and handshake(result) == list(reversed(actions)):
        result |= 16

    return bin(result)[2:]
