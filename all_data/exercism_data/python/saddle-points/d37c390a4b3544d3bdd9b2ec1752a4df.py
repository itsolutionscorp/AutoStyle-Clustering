def _list_is_irregular(my_list):
    if my_list != []:
        row_length = len(my_list[0])
        for i in range(1, len(my_list)):
            if row_length != len(my_list[i]):
                return True
    return False


def _is_column_min(my_list, num, col):
    list_col = [ my_list[row][col] for row in range(len(my_list)) ]
    return num == min(list_col)


def saddle_points(my_list):

    if _list_is_irregular(my_list):
        raise ValueError('irregular matrix')

    s_points = []
    for row in range(len(my_list)):
        row_max = max(my_list[row])
        for col in range(len(my_list[row])):
            if row_max == my_list[row][col]:
                row_max_index = (row, col)
                if _is_column_min(my_list, row_max, col):
                    s_points.append(row_max_index)

    return set(s_points)
