import math

_commands = [
    "wink", # 1
    "double blink", # 2
    "close your eyes", # 4
    "jump" # 8
]

def handshake(num):
    indices = []

    if isinstance(num, (int, long)): # num is an integer
        indices = _parse_num_to_bin_indices(num)
    elif isinstance(num, basestring):
        indices = _parse_str_to_bin_indices(num)
    else:
        raise ValueError("can only parse integers and strings")

    if 4 in indices:
        indices.remove(4)
        indices = list(reversed(indices))

    return [ _commands[i] for i in indices ]


def _parse_num_to_bin_indices(num):
    indices = []

    while num > 0:
        power = int(math.floor(math.log(num, 2)))
        indices.insert(0, power)
        num -= 2**power

    return indices


def _parse_str_to_bin_indices(b_str):
    indices = []

    for i, b in enumerate(reversed(b_str)):
        if b not in [ "0", "1" ]:
            # not valid binary
            return []

        if b == "1":
            indices.append(i)

    return indices



def code(commands):
    indices = [ _get_command_position(c) for c in commands ]
    if -1 in indices:
        # an invalid command was passed
        return "0"

    sort = sorted(indices)
    reverse = list(reversed(sort))

    if indices == sort:
        return _generate_binary_string(indices)
    elif indices == reverse:
        indices.append(4) # the index for reversed
        return _generate_binary_string(indices)
    else:
        # order isn't correct
        return "0"


def _get_command_position(command):
    if command not in _commands:
        return -1
    return _commands.index(command)


def _generate_binary_string(indices):
    length = max(indices)
    return "".join( "1" if i in indices else "0" for i in range(length, -1, -1) )
