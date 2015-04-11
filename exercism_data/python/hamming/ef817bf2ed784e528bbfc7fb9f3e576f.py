def hamming(a_string, o_string):
    if len(a_string) <= len(o_string):
        common_len = len(a_string)
        diff = len(o_string) - len(a_string)
    else:
        common_len = len(o_string)
        diff = len(a_string) - len(o_string)

    for i in range(common_len):
        if a_string[i] != o_string[i]:
            diff += 1
    return diff
