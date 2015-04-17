def num_common_letters(goal_word, guess):
    total_len = 0
    temp_list = []
    for i in get_list(goal_word):
        if i in get_list(guess):
            temp_list+=(i)
            total_len += 1
    return total_len    
