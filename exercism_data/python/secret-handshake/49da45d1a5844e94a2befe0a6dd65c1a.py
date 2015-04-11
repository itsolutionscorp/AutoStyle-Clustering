

CODE_MAP = ["wink","double blink","close your eyes","jump"]

def handshake(code):
    """code is :
    either a string made of 1s and 0s
    or an integer that wil be converted to bin"""
    if type(code) is str:
        if any(c not in ("10") for c in code):
            return []
        # any suggestion for improving this conversion ?
        code = sum(int(c)*2**(idx) for idx,c in enumerate(code[::-1]))
    if code < 0 : return []
    # code is now an int.
    output = [action for (shift, action) in enumerate(CODE_MAP)
                     if code >> shift & 1]  # use binary shift to map
    return output if code < 16 else output[::-1]  # reverse output if needed


def code(action_sequence):
    """Returns a code description of the given handshake
    """
    # refuse any invalid action :
    if set(action_sequence) - set(CODE_MAP):
        return '0'  # per spec (test_unknown_action)

    mapdict = dict(zip(CODE_MAP,(1,2,4,8)))
    da_code = [mapdict[action] for action in action_sequence]
    if sorted(da_code) != da_code:  # list is not sorted : assume reversed
        da_code.append(16)
    return bin(sum(da_code))[2:]  # sum to integer, binstring, and return
