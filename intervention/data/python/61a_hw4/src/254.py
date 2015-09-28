def num_common_letters(goal_word, guess):
    correct = 0
    for letter in get_list(make_word_from_string(goal_word)):
        if letter in get_list(make_word_from_string(guess)):
            correct += 1
    return correct
