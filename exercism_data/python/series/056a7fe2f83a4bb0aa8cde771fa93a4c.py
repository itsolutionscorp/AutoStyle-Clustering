def make_int(piece):
    p_index = 0
    while p_index < len(piece):
        piece[p_index] = int(piece[p_index])
        p_index += 1
    return piece

def slices(str, n):
    if n > len(str) or n < 1:
        raise ValueError("n is too long or too short")
    results = []
    index = 0
    for e in str:
        piece = str[index:index + n]
        piece = list(piece)
        piece = make_int(piece)
        if piece not in results:
            results.append(piece)
        index += 1
        if index + n > len(str):
            break
    return results
