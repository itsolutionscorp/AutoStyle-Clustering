def distance(input1, input2):
    result = 0
    for i, char in enumerate(input1):
        if char != input2[i]:
            result += 1

    return result
