def num_common_letters(a,b):
	blist = set()
	[blist.add(l) for l in a if l in list(b) and l not in blist]
	return len(blist)

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...not using a call to list....not using list comprehension....not using the in operator.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
