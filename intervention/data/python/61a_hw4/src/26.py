def num_common_letters(goal_word, guess):
    correct_letters = 0
    correct_letters_list = []
    goal_word_list = get_list(goal_word)
    guess_list = get_list(guess)
    for letter in guess_list:
        if letter in correct_letters_list:
            correct_letters += 0
        else:
            if letter in goal_word_list:
                correct_letters += 1
                correct_letters_list += letter
    return correct_letters
