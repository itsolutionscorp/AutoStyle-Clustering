def saddle_points(array):
    points = set()
    if array:
        try:
            row_max = list(enumerate([max(row) for row in array]))
            column_min = list(enumerate([column_min_func(array, i) 
                                            for i in range(len(array[0]))]))
            for a, b in row_max:
                for c, d in column_min:
                    if b == d:
                        points.add((a, c))
        except IndexError:
            raise ValueError
    return points
    
def column_min_func(matrix, i):
    return min([row[i] for row in matrix])
