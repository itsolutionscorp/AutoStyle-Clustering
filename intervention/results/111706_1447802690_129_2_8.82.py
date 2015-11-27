def num_common_letters(a,b):
	blist = list()
	btemp = [ele for ele in b]
	for l in a:
		if l in btemp and l not in blist:
			blist+=l
	return len(blist)

Positive Hints
...using a call to set....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to list....not using list comprehension.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
