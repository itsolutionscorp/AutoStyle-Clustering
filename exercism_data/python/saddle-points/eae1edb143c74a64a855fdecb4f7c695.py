import numpy as np

def saddle_points(matrix):
    new_matrix = np.array(matrix)
    exp_length = len(new_matrix)
    check_dimensions(new_matrix,exp_length)
    return_value = set()
    for row in xrange(0,exp_length):
        for col in xrange(0,exp_length):
            curr = new_matrix[row][col]
            min_col = np.amin(new_matrix[:,col])
            max_row = np.amax(new_matrix[row,:])
            if max_row == min_col == curr:
                return_value.add((row,col))
    return return_value

def check_dimensions(new_matrix, exp_length):
    try:
        for elm in xrange(0,exp_length):
            new_matrix[:,elm]
            new_matrix[elm,:]
    except:
        raise ValueError
