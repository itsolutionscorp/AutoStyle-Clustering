def distance(first_strand, second_strand):
    distance_count = 0
    for one, two in zip(first_strand, second_strand):
        if one != two: distance_count += 1
    return distance_count
