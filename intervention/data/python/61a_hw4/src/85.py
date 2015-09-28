def num_common_letters(goal_word, guess):
    index = 0
    counter = 0 
    size = len(goal_word)
    while index < size:
        if goal_word[index] in guess:
            counter = counter + 1
        index = index + 1
    return counter
