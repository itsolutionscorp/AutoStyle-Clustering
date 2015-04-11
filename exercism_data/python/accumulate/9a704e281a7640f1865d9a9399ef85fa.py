def accumulate(my_list, func):
    new_list = [func(x) for x in my_list]
    return new_list
