dic = {
    '1': ["   ", "  |", "  |", "   "],
    '0': [" _ ", "| |", "|_|", "   "]}


def number(what):
    if len(what) != 4 or len(max(what)) != len(min(what)):
        raise ValueError
    for k, v in dic.items():
        if v == what:
            return k
    else:
        return '?'


def grid(what):
    return dic[what]
