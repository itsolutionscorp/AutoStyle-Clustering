def num_common_letters(goal_word, guess):
    letters = []
    count = 0
    for letter in goal_word:
        if letter in guess and letter not in letters:
            count += 1
            letters += str(letter)
    return count


Positive Hints
...using a call to len....using a call to set....using list comprehension.

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to str.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
