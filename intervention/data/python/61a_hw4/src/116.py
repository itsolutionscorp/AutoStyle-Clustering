def num_common_letters(goal_word, guess):
    letters = []
    match_lets = 0
    for item in get_string(guess):
        if item not in letters and item in get_string(goal_word):
            match_lets += 1
            letters += [item]
    return match_lets
