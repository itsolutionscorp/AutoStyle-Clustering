def num_common_letters(goal_word, guess):
    uni_common_list = []
    l1=list(''.join(goal_word.split()))
    l2=list(''.join(guess.split()))
    common_list = [x for x in l1 if x in l2]
    common_set = set(common_list)
    return len(common_set)

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using a call to set....not using a call to list....not using list comprehension....not using the in operator.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
