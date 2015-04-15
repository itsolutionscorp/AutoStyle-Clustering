def sieve(n):
    # Generate a list of all the integers up to n inclusive.
    cands = [x for x in range(2, n+1)]

    # Iterate through cands, applying sieve.
    for cand in cands:
        # Check all remaining candidates.
        for cand_div in cands[cands.index(cand)+1:]:
            if cand_div % cand == 0:
                # cand_div is divisor so is removed.
                cands.remove(cand_div)

    return cands
