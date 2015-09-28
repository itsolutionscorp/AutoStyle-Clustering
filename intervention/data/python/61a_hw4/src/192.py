def num_common_letters(goal_word, guess):
    total_number_of_common_letters = 0
    for element in goal_word:
        if element == element in guess:
            total_number_of_common_letters += 1
    return total_number_of_common_letters
