def accumulate(lst, op):
    result = []
    for el in lst:
        result.append(op(el))
    return result
