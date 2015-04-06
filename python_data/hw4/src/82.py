def num_common_letters(goal_word, guess):
    goal_list = get_list (goal_word)
    guess_list = get_list (guess)
    common_list = []
    for i in range (0, len (goal_list)):
        for j in range (0, len (guess_list)):
            if (goal_list[i] == guess_list[j] and guess_list[j] not in common_list):
                common_list += guess_list [j]
    return len (common_list)
