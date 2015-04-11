def distance(first, second):
    combined = zip(first, second)
    num_diff = filter(lambda char: char[0] != char[1], combined)

    return len(num_diff)
