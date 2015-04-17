def num_common_letters(goal_word, guess):
    count = 0
    for i in range(len(get_string(goal_word))):
        if get_string(goal_word[i]) in get_string(guess):
            count +=1
    return count
