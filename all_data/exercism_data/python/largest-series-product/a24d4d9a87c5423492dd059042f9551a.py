def slices(seq, n):
    if n > len(seq) or n < 1:
        raise ValueError("Impossible to find series of length %d in sequence of length %d" %(n, len(seq)))
    seq = list(map(int, seq))
    return [seq[i:i+n] for i in range(len(seq)-n+1)]

def largest_product(seq, n):
    if not seq:
        return 1
    sub_seqs = slices(seq, n)
    return max([prod(sub) for sub in sub_seqs])

def prod(nums):
    p = 1
    for i in nums:
        p *= i
    return p
