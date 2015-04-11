def nothing(m):
    m = m.replace(" ", "")
    if len(m) == 0: return True
    for c in m:
        if m in [ '\t' ]:
            return True
    return False

def screams(m):
    if m.isupper(): return True
    return False

def question(m):
    if m[-1] == '?':
        return True
    else:
        return False

def hey(m):
    if nothing(m):
        answer = 'Fine. Be that way!'
    elif screams(m):
        answer = 'Woah, chill out!'
    elif question(m):
        answer = 'Sure.'
    else:
        answer = 'Whatever.'
    return answer
