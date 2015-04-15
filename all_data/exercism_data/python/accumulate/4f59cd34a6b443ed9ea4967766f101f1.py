#accumulate = lambda list, func: map(func, list)
accumulate = lambda list, func: [func(el) for el in list]
