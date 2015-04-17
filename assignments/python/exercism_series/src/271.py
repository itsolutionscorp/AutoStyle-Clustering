def slices(digits, N):
    if not N:
        raise ValueError("N=0")
    if N > len(digits):
        raise ValueError("N={} > len(digits)={}".format(N, len(digits)))
    digits = [int(d) for d in digits]
    return [digits[i:i+N] for i in range(len(digits)-N+1)]
