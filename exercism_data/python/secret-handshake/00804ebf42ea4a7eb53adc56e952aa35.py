ACTIONS = {1: "wink", 2: "double blink", 3: "close your eyes",
            4:"jump", 5:"Reverse"}

def handshake(code):
    if isinstance(code, (int, long)):
        if code <= 0:
            return []
        code = bin(code)[2:]

    try:
        int(code, 2) # ensure only binary numbers are in str
    except:
        return []

    results = [ACTIONS[i] for i in range(len(code), 0, -1) if code[-i] == '1']
    if "Reverse" in results:
        results.remove("Reverse")
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
