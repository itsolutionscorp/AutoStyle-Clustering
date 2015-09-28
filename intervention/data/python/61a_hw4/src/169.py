def num_common_letters(goal_word, guess):
    goal_word_list, guess_list = get_list(goal_word), get_list(guess)
    return len([letter for letter in goal_word_list if letter in guess_list])
