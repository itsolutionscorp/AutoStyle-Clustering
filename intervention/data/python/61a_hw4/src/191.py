def num_common_letters(goal_word, guess):
    counter = 0
    for char in get_list(goal_word):   # if true 
        if char in get_list(guess):    # what if i use set
            counter += 1
    return counter
