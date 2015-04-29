'''
	Calculate the hamming distance between 2 DNA strands
'''

# def distance(DNA1, DNA2):
# 	count = 0;
# 	for c in range(len(DNA1)):
# 		if (DNA1[c] != DNA2[c]):
# 			count += 1;
# 	return count


# Method 2
# def distance(a, b):
# 	count = 0;
# 	return sum(count+1 for c in range(len(a)) if (a[c] != b[c]))

# Method 3
def distance(DNA1, DNA2):
    return sum(1 for a, b in zip(DNA1, DNA2) if (a != b))
