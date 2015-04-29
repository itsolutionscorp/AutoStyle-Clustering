
def slices(value, length):
    if length > len(value) or length == 0:
        raise ValueError('check value and length')
    else:
        out, value = [], [int(i) for i in value]
        for i in range((len(value) - length) + 1):
            out.append(value[i:i+length])
        return out
