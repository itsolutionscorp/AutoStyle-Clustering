import numpy as np

def saddle_points(matrix):
	if not matrix:
		return set()
	a = np.array(matrix)
	row_max = np.repeat(np.amax(a,axis=1),a.shape[1]).reshape(a.shape)
	col_min = np.tile  (np.amin(a,axis=0),a.shape[0]).reshape(a.shape)
	min_max = np.logical_and(a == col_min, a == row_max)
	return set(zip(*np.where(min_max == True)))
