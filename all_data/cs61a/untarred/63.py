def num_common_letters(goal_word, guess):
    guess_word = get_string(guess)
    final_guess_word = ''
    for letter in guess_word:
        if (letter not in final_guess_word):
            final_guess_word += letter
    guess_list = get_list(guess)
    number_of_common_letters = 0
    for y in goal_word:
        for x in final_guess_word:
            if (x == y):
                number_of_common_letters += 1
    return number_of_common_letters
