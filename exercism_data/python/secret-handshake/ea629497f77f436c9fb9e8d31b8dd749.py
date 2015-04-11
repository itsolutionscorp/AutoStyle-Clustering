ACTIONS = [
        lambda x: x + ['wink'],
        lambda x: x + ['double blink'],
        lambda x: x + ['close your eyes'],
        lambda x: x + ['jump'],
        lambda x: list(reversed(x))
        ]

def handshake(n):
    def aux(n, actions, acc):
        if n <= 0:
            return acc
        else:
            if n % 2 != 0:
                acc = actions[0](acc)
            return aux(int(n/2), actions[1:], acc)
    def to_int(n):
        if type(n) is int:
            return n
        elif any([x != '0' and x != '1' for x in n]):
            return 0
        else:
            return sum([2 ** (len(n) - i - 1) for i in range(len(n)) if n[i] == '1'])

    return aux(to_int(n), ACTIONS, [])

def code(shake):
    def possible(lst):
        if len(lst) == 0:
            return True
        if lst == shake[:len(lst)]:
            return True
        if lst == list(reversed(shake[-len(lst):])):
            return True
        return False
    def aux(actions, lst, value):
        if lst == shake:
            return value
        elif len(actions) == 0:
            return None
        elif possible(lst):
            result_apply = aux(actions[1:], actions[0](lst), '1' + value)
            if result_apply is not None:
                return result_apply
            else:
                return aux(actions[1:], lst, '0' + value)
        else:
            return None

    result = aux(ACTIONS, [], '')
    return result if result is not None else '0'
