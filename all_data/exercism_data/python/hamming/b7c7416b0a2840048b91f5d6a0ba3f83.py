def distance(strand_one, strand_two):
    # returns tuples of each seq pairing
    zip_seq = [seq for seq in zip(strand_one, strand_two)]
    count = 0
    for first, second in zip_seq:
        if first != second:
            count += 1
    return count
