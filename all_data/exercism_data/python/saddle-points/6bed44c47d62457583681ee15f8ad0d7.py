def which_extr(mat, fcn):
	extr = [fcn(r) for r in mat]
	return [
	        [mat[i][j] == extr[i] for j in range(len(mat[0]))]
	        for i in range(len(mat))
	       ]
		
def saddle_points(rmat):
	if not rmat:
		return set()
	for r in rmat:
		if len(r) != len(rmat[0]):
			raise ValueError('Not all rows are the same length.')
	r = len(rmat)
	c = len(rmat[0])
	cmat = [
	        [rmat[j][i] for j in range(r)]
	         for i in range(c)
	       ]

	rmax = which_extr(rmat, max)
	cmin = which_extr(cmat, min)
				
	return set([(i,j) for i in range(r) for j in range(c)
	            if rmax[i][j] and cmin[j][i]])
