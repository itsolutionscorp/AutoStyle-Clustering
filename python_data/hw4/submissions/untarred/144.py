def num_common_letters(goal_word, guess):
    counter = 0
    for elem in letters:
        if elem in guess and elem in goal_word:
            counter += 1
    return counter
   
