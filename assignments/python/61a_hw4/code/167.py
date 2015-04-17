def num_common_letters(goal_word, guess):
    if get_list(goal_word) == []:
        return 0
    for i in range(len(get_list(guess))):
        if get_list(guess)[i] == get_list(goal_word)[0]:
            return 1 + num_common_letters(make_word_from_list(get_list(goal_word)[1:]), make_word_from_list(get_list(guess)))
    return num_common_letters(make_word_from_list(get_list(goal_word)[1:]), make_word_from_list(get_list(guess)))
