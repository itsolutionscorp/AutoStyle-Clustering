alist = ['wink', 'double blink', 'close your eyes', 'jump']

def handshake(code):

    actions = []
    if type(code) == int:
        if code > 31 or code < 0:
            code = '0'
        else:
            code = bin(code)[2:]
    elif type(code) != str:
        raise ValueError("Argument must be a number or string.")

    codelist = list(code)
    if len(codelist) <= 5 and len(codelist) == codelist.count('0') + codelist.count('1'):
        if codelist and codelist.pop() == '1':
            actions.append(alist[0])
        if codelist and codelist.pop() == '1':
            actions.append(alist[1])
        if codelist and codelist.pop() == '1':
            actions.append(alist[2])
        if codelist and codelist.pop() == '1':
            actions.append(alist[3])
        if codelist and codelist.pop() == '1':
            actions.reverse()

    return actions


def code(actions):
    action_nums = []
    for action in actions:
        if action not in alist:
            return '0'
        action_nums.append(alist.index(action))
    decimalcode = 0
    for num in action_nums:
        decimalcode += 2**num
    if sorted(action_nums) != action_nums:
        decimalcode += 2**4
    return bin(decimalcode)[2:]
