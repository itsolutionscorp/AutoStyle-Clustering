
def score(word=''):
    out = 0
    letter = {
        1: ['a', 'e', 'i', 'o', 'u', 'l', 'n', 'r', 's', 't'],
        2: ['d', 'g'],
        3: ['b', 'c', 'm', 'p'],
        4: ['f', 'h', 'v', 'w', 'y'],
        5: ['k'],
        8: ['j', 'x'],
        10: ['q', 'z']
    }

    for i in word.lower():
        for j, v in letter.items():
            if i in v:
                out += j
    return out
