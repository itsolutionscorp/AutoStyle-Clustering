def num_common_letters(goal_word, guess):
    goal_str = get_string(goal_word)
    guess_str = get_string(guess)
    common = 0
    for letter in goal_str:
        if letter in guess_str:
            common += 1
    return common
