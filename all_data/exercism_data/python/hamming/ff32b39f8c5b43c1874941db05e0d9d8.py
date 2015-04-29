def distance(u, v):
    if len(u) != len(v):
        raise ValueError("Sequence must be the same length")
    return sum(u_nuc != v_nuc for u_nuc, v_nuc in zip(u,v))
