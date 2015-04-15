def string_to_ascii_arr(input_str):
    return [ord(char) for char in input_str]


def distance(seqA, seqB):
    arrA = string_to_ascii_arr(seqA)
    arrB = string_to_ascii_arr(seqB)
    return sum([1 for i in range(0, len(arrA)) if arrA[i] != arrB[i]])
