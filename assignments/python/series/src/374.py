def slices(string, num):
    results = []
    if num > len(string) or not num:
        raise ValueError
    else:
        for x in range(len(string)):
            s = string[x:num+x]
            if len(s) == num:
                s = [int(x) for x in s]
                results.append(s)
        return results
