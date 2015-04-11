def accumulate(list, fun):
    if list == []:
        return []
    else:
        return [fun(x) for x in list]
