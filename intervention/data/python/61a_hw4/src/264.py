def num_common_letters(goal_word, guess):
    match = 0
    i = 0
    goal_list = get_list(goal_word)
    guess_list = get_list(guess)
    while i < len(goal_list):
        j = 0
        while j < len(guess_list):
            if goal_list[i] == guess_list[j]:
                match += 1
                j = len(guess_list)
            j += 1
        i += 1
    return match 
