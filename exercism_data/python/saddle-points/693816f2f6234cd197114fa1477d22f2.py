
def saddle_points(matrix):
    if len(set(len(row) for row in matrix)) > 1:
        raise ValueError("Not a well formed matrix")
        
    return set((row_index, col_index)
               for row_index, row in enumerate(matrix)
               for col_index, val in enumerate(row)
               if max(row) == val
               if min(row[col_index] for row in matrix) == val)
