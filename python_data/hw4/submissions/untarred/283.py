def num_common_letters(goal_word, guess):
    goal_list, guess_list = get_list(goal_word), get_list(guess)
    num_common = 0
    for letter in goal_list:
        if letter in guess_list:
            num_common += 1
    return num_common
