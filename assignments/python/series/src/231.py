def slices(string, length):
    if length <= 0:
        raise ValueError('Required length of slice too short')
    if length > len(string):
        raise ValueError('Required length of slice too long')
    return [map(int, list(string[i:i + length])) for i in range(len(string) - length + 1)]
