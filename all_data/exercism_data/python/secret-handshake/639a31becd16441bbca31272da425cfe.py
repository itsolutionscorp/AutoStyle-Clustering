CODE_MAP = ["wink", "double blink", "close your eyes", "jump"]


def handshake(codespec):
    """code is :
    either a string made of 1s and 0s
    or an integer that wil be converted to bin"""
    if isinstance(codespec, str):
        if any(c not in "10" for c in codespec):
            return []
        # any suggestion for improving this conversion ?
        codespec = int(codespec, 2)
    if codespec < 0:
        return []

    output = [action
              for (shift, action) in enumerate(CODE_MAP)
              if codespec >> shift & 1]  # use binary shift to map
    return output if codespec < 16 else output[::-1]  # reverse output if needed


def code(action_sequence):
    """Returns a code description of the given handshake
    """
    # refuse any invalid action :
    if set(action_sequence) - set(CODE_MAP):  # some action was not in the known ones.
        return '0'  # per spec (test_unknown_action)

    mapdict = dict(zip(CODE_MAP, (1, 2, 4, 8)))
    da_code = [mapdict[action] for action in action_sequence]
    if sorted(da_code) != da_code:  # list is not sorted : assume reversed
        da_code.append(16)
    return format(sum(da_code), 'b')  # sum to integer, binstring, and return
