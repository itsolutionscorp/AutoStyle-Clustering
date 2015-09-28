def num_common_letters(goal_word, guess):
    common_letters = []
    for letter in get_list(goal_word):
        if letter in get_list(guess):
            common_letters += [letter]
    return len(common_letters)
