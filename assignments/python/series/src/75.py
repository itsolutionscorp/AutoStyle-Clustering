def slices(series, length):
        how_many_slices = len(series) - length + 1
        if how_many_slices <= 0 or length == 0:
                raise ValueError
        list_of_slices = []
        for i in range(0, how_many_slices):
                list_of_slices.append([int(series[j]) for j in
                                      range(i, i+length)])
        return list_of_slices
