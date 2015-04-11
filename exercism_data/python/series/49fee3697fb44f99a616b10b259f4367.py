def slices(w, n):
    return_list = []

    if n > len(w) or n == 0:
        raise ValueError("cheeky little rascal")

    for char in range(len(w) - n + 1):
        temp_list = []
        for pair in range(n):
            temp_list.append(int(w[char+pair]))
        return_list.append(temp_list)

    return return_list
