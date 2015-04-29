def hamming(a, b):
    ans = abs(len(a)-len(b))

    length = min(len(a), len(b))
    for i in range(length):
        if a[i] != b[i]:
            ans += 1

    return ans
