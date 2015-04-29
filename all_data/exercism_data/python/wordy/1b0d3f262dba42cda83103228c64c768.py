validops = ['plus', 'minus', 'divided', 'multiplied']


def calculate(exp):
    if exp.startswith('What is '):
        exp = exp[8:len(exp)-1]
        tokengen = gettokens(exp)
        st = list()

        for t in tokengen:
            if isnum(t):
                st.append(int(t))

            elif isop(t):
                if t == 'multiplied' or t == 'divided':
                    bytok = next(tokengen)
                    if bytok != 'by':
                        raise ValueError

                numtok = next(tokengen)

                if not isnum(numtok):
                    raise ValueError

                num = int(numtok)

                st.append(execop(t, st.pop(), num))

            else:
                raise ValueError

        if len(st) != 1:
            raise ValueError

        return st.pop()

    else:
        raise ValueError


def isnum(t):
    try:
        int(t)
        return True
    except ValueError:
        return False


def execop(op, a, b):
    if op == 'plus':
        return a + b
    elif op == 'minus':
        return a - b
    elif op == 'multiplied':
        return a * b
    elif op == 'divided':
        return a // b
    return 0


def isop(t):
    return t in validops


def gettokens(exp):
    toks = exp.split()
    i = 0
    while i < len(toks):
        yield toks[i]
        i += 1
