def detect_anagrams(ref,compare):
    ref_length = len(ref)
    ref_sorted = sorted(ref.lower())
    out = []
    
    for c in compare:
        if len(c) == ref_length and c.lower() != ref.lower():
            if sorted(c.lower()) == ref_sorted:
                out.append(c)
    return out
