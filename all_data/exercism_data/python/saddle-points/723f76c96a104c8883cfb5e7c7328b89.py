def saddle_points(matrix):
	matrix=matrix
	if len(matrix) <1:
		#print 'length too short'
		return set()
	testinput(matrix)
	row=[]
	column=[]
	result=[]
	i=j=0
	zipped = (zip(*matrix))
	trans=[]
	[trans.append(list(ele)) for ele in zipped]
	#print matrix
	for ele in matrix:
		#row.append([i,indexss(max(ele),ele)])
		max_inx = indexss(max(ele),ele)
		#print max_inx
		for indx in max_inx:
			row.append([i,indx])
		i+=1
	for ele in trans:
		#column.append([indexss(min(ele),ele),j])
		#print trans
		min_inx = indexss(min(ele),ele)
		for indx in min_inx:
			column.append([indx,j])
		j+=1
	#print '+++'
	#print row
	#print column
	#print '+++'
	for index in row:
		if index in column:
			result.append(tuple(index))
			#return index
	#print '+++'
	print set(result)
	return set(result)

def testinput(matrix):

	set1=set()
	set1.add(len(matrix[0]))
	for ele in matrix:
		if len(ele) not in set1:
			raise ValueError, "input is not matrix"
	return

def indexss(num, lists):

	result=[]
	#lists= [lists]
	#print type(lists)
	#print lists.index(num)
	while num in lists:
		i=lists.index(num)
		result.append(i)
		lists.remove(num)
		lists.insert(i,num-1)
	#print result,'result is here'
	return result
