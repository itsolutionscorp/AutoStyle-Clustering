import re


def board(inp):
    out = list()

    if not validateboard(inp):
        raise ValueError

    out.append(inp[0])

    for i in range(1, len(inp) - 1):
        s = "|"
        for j in range(1, len(inp[0]) - 1):
            mines = 0

            if inp[i][j] == "*":
                s += "*"
                continue

            for di in range(-1, 2):
                for dj in range(-1, 2):
                    if (di != 0 or dj != 0) and inp[i + di][j + dj] == "*":
                        mines += 1

            if mines > 0:
                s += str(mines)
            else:
                s += ' '

        s += '|'
        out.append(s)

    out.append(inp[0])

    return out


def validateboard(inp):
    if len(inp) == 0:
        return False

    rowlen = len(inp[0])

    for r in range(0, len(inp)):
        if len(inp[r]) != rowlen:
            return False

        if (r == 0 or r == len(inp) - 1):
            if re.match("^\+-+\+$", inp[r]) is None:
                return False
        else:
            pat = "^\|[ *]{" + str(rowlen - 2) + "}\|$"
            if re.match(pat, inp[r]) is None:
                return False

    return True
