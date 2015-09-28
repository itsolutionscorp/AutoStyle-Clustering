def num_common_letters(goal_word, guess):
    number_of_letters = 0
    for item in get_string(goal_word):
        if item in get_list(guess):
            number_of_letters += 1
    return number_of_letters
