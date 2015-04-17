def slices(series,length):
    if len(series) < length or length == 0:
        raise ValueError
    main_list= []   
    while len(series) >= length:
        main_list.append([int(series[index]) for index in range(length)])
        series = series[1:]
    return main_list
