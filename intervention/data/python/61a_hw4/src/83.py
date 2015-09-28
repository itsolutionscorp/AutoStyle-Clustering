def num_common_letters(goal_word, guess):
    count = 0
    for letter in get_list(goal_word):
        if letter in get_list(guess):
            count = count + 1
    return count 
