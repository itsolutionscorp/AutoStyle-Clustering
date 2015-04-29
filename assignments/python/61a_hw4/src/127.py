def num_common_letters(goal_word, guess):
    if get_list(goal_word)==[]:
        return 0
    if get_list(goal_word)[0] in get_list(guess):
        return 1 + num_common_letters(make_word_from_list(get_list(goal_word)[1:]),guess)
    else:
        return num_common_letters(make_word_from_list(get_list(goal_word)[1:]),guess)
