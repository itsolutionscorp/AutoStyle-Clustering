def accumulate(lista, function):
    new_list = []
    for i in lista:
        new_list.append(function(i))
    return new_list
