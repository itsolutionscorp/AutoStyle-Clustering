def slices(series, digit):
    if digit > len(series):
        raise ValueError("Series is too short")
    if digit == 0:
        raise ValueError("No digit given")

    result = []

    if digit == 1:
        result = [[int(x)] for x in series]

    if digit == 2:
        a = [int(x) for x in series]
        b = [int(x) for x in series[1::]]
        z = [a,b]
        result = [[a,b] for a,b in zip(*z)]

    if digit == 3:
        a = [int(x) for x in series]
        b = [int(x) for x in series[1::]]
        c = [int(x) for x in series[2::]]
        z = [a,b,c]
        result = [[a,b,c] for a,b,c in zip(*z)]

    if digit == 4:
        a = [int(x) for x in series]
        b = [int(x) for x in series[1::]]
        c = [int(x) for x in series[2::]]
        d = [int(x) for x in series[3::]]
        z = [a,b,c,d]
        result = [[a,b,c,d] for a,b,c,d in zip(*z)]

    if digit >= 5:
        a = [int(x) for x in series]
        b = [int(x) for x in series[1::]]
        c = [int(x) for x in series[2::]]
        d = [int(x) for x in series[3::]]
        e = [int(x) for x in series[4::]]
        z = [a,b,c,d,e]
        result = [[a,b,c,d,e] for a,b,c,d,e in zip(*z)]

    return result
