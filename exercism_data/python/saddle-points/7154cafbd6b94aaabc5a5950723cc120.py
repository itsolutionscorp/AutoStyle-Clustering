def saddle_points(array):
    points = set()
    if array:        
        if any(len(line) != len(array[0]) for line in array):
            raise ValueError
        else:       
            row_max = list(enumerate([max(row) for row in array]))
            
            colminf = lambda matrix, i: min([row[i] for row in matrix])
            column_min = list(enumerate([colminf(array, i) 
                                            for i in range(len(array[0]))]))
                                            
            for row_place, row_value in row_max:
                for col_place, col_value in column_min:
                    if row_value == col_value:
                        points.add((row_place, col_place))
    return points
