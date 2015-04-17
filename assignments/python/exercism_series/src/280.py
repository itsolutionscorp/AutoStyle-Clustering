def slices(what,step):
    if step > len(what) or step == 0:
        raise ValueError
    list_of_tupler = list()
    in_list= list()
    for i in range(len(what)+1):
        if step<=len(what) - (i-1):
            nmb = what[i-1:step+i-1:]
            for i in range(len(nmb)):
                in_list.append(int(nmb[i:i+1:]))
            list_of_tupler.append(in_list)
            in_list = []
    return list_of_tupler[1::]
