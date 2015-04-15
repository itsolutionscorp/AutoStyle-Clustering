def distance(left_seq, right_seq):
    # raise the error loudly if different distance

    # otherwise
    distance = 0
    for idx, lval in enumerate(left_seq):
	if right_seq[idx] != lval: distance += 1	
    return distance
