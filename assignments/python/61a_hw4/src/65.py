def num_common_letters(goal_word, guess):
    return len(set([x for x in get_string(guess) if x in get_string(goal_word)]))
