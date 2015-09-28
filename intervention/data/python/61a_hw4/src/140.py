def num_common_letters(goal_word, guess):
    k = 0
    for elem in goal_word:
        if elem in guess:
            k += 1 #don't make a counter here 
        else:
            k += 0
    return k 
