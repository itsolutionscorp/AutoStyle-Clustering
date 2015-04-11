def distance(str1, str2):
    str_len = len(str1)

    dist = 0  # Initial distance (difference btn strings) is zero

    for i in xrange(str_len):
        if str1[i] != str2[i]:
            dist += 1  # Increment distance by 1 for each difference

    return dist
