def num_common_letters(goal_word, guess):
    return len([[x] for x in get_list(goal_word) if x in get_list(guess)])
