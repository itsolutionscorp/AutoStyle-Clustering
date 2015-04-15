def distance(strand_one, strand_two):
    # iterates over each strand in list of tuples (zip) for each strand
    # increase count based on matches
    count = 0
    for first, second in zip(strand_one, strand_two):
        if first != second:
            count += 1
    return count
