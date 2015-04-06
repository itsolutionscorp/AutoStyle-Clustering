def num_common_letters(goal_word, guess):
    return len([word for word in get_list(goal_word) if word in get_list(guess)])
