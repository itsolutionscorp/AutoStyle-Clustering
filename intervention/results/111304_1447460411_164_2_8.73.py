def num_common_letters(goal_word, guess):
    uni_common_list = []
    s1=set(list(goal_word))
    s2=set(list(guess))
    common_set = [x for x in s1 if x in s2]
    return len(common_set)


Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using a call to set....not using a call to list....not using list comprehension....not using the in operator.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
