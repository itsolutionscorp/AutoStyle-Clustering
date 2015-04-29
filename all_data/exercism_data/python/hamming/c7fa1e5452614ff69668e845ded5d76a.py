def distance(seq_orig, seq_copy):
    mutation = 0
    for i in range(len(seq_orig)):
        if seq_orig[i] != seq_copy[i]:
            mutation = mutation + 1
    return mutation
