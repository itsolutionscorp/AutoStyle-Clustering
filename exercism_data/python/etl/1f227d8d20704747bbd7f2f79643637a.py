'''
transform dictionary representation of data by switching keys and values
values are guaranteed to be unique
convert all values to lowercase
'''
def transform(old_dict):
	newdict = {}
	for newkeys,newval in zip(old_dict.values(),old_dict.keys()):
		for newkey in newkeys:
			newdict[newkey.lower()] = newval
	return newdict

if __name__=='__main__':
	old = {1: ['WORLD']}
	print(transform(old))
	old = {1: ['WORLD', 'GSCHOOLERS']}
	print(transform(old))
	old = {1: ['APPLE', 'ARTICHOKE'], 2: ['BOAT', 'BALLERINA']}
	print(transform(old))
	old = {
	    1: "AEIOULNRST",
	    2: "DG",
	    3: "BCMP",
	    4: "FHVWY",
	    5: "K",
	    8: "JX",
	    10: "QZ",
	}
	print(transform(old))
