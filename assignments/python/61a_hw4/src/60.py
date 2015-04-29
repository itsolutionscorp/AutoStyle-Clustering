def num_common_letters(goal_word, guess):
    count=0
    for gc in get_string(guess):
        if gc in get_string(goal_word):
            count=count+1
    return count
