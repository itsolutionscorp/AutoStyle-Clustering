def get_length_diff(left_strand, right_strand):
    left_len = len(left_strand)
    right_len = len(right_strand)
    return max(left_len, right_len) - min(left_len, right_len)

def hamming(left_strand, right_strand):
    hamming_vector = [int(base_pair[0] != base_pair[1]) for base_pair in zip(left_strand, right_strand)]
    return reduce(lambda x,y: x+y, hamming_vector, 0) + get_length_diff(left_strand, right_strand) 
