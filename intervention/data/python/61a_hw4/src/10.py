def num_common_letters(goal_word, guess):
    given = get_list(goal_word)
    pattern = get_list(guess)
    result = []
    return len(set(given).intersection(pattern))
