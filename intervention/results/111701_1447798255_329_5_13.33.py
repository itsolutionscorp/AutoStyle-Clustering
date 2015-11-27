def num_common_letters(goal_word, guess):
    # add your code here
    # restrict your code to a single function
    repeated = []

    while goal_word != '':
        letter = goal_word[0]
        if letter in guess and letter not in repeated:
            repeated.append(letter)
        goal_word = goal_word[1:]

    return len(repeated)

Positive Hints
...using the augassign operator....using a call to set....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to len.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
