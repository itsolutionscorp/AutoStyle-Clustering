def saddle_points(mtx):
    if any(len(row) != len(mtx[0]) for row in mtx):
        raise ValueError

    if mtx == []:
        return set()

    max_of_row = [(x,y) for x, row in enumerate(mtx) for y, col in enumerate(row) if col == max(row)]
    mtxT = [column for column in zip(*mtx)]
    min_of_col = [(y,x) for x, row in enumerate(mtxT) for y, col in enumerate(row) if col == min(row)]

    return set([x for x in min_of_col if x in max_of_row])
