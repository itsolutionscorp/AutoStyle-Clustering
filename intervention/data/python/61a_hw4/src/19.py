def num_common_letters(goal_word, guess):
    goal_word_list = get_list(goal_word)
    guess_string = get_string(guess)
    common_num = 0
    for s in goal_word_list:
        if s in guess_string:
            common_num += 1
    return common_num
