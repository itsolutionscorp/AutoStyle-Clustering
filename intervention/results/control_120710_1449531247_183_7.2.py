def num_common_letters2(string1, string2):
    count = 0
    for letter in set(string1):
        if letter in set(string2):
            count += 1
    return count