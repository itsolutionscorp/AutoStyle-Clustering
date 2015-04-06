def num_common_letters(goal_word, guess):
    common_letters = 0
    guess_list = get_list(guess)
    goal_list = get_list(goal_word)
    for i in range(len(goal_list)):
        if goal_list[i] in guess_list:
            common_letters += 1
        else: 
            common_letters += 0
    return common_letters
