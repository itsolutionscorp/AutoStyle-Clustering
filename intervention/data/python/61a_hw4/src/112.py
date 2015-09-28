def num_common_letters(goal_word, guess):
    guess_list = get_list(guess)
    goal_list = get_list(goal_word)
    letters_in_common = 0
    for i in range(len(goal_list)):
        if goal_list[i] in guess_list:
            letters_in_common += 1
        else:
            letters_in_common += 0 
    return letters_in_common 
