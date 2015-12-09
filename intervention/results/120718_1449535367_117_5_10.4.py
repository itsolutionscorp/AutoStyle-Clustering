def num_common_letters(goal_word, guess):
    count = 0
    word_list = []
    for i in range(len(goal_word)):
        if goal_word[i] in list(guess) and goal_word[i] not in word_list:
            word_list += goal_word[i]
            count += 1
    return count

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set.....using set comprehension....using a call to set.

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to len....not using a call to list.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
