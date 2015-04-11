ACTIONS = {1: "wink", 2: "double blink", 3: "close your eyes",
            4:"jump", 5:"reverse"}

def handshake(code):
    if isinstance(code, (int, long)):
        if code <= 0:
            return []
        code = bin(code)[2:]

    if any(c not in "10" for c in code):
            return []

    results = [ACTIONS[i] for i in range(len(code), 0, -1) if code[-i] == '1']
    if "reverse" in results:
        results.remove("reverse")
        return results
    return results[::-1]

def code(action_list):
    try:
        CODE = dict(zip(ACTIONS.values(),ACTIONS.keys()))
        codes = [CODE[x] for x in action_list]
        result =  ''.join(['1' if i in codes else '0' for i in range(1,max(codes)+1)][::-1])
        if sorted(codes) == codes:
            return result
        return '1'+ result
    except KeyError:
        return '0'
