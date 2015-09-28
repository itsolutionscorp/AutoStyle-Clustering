def num_common_letters(goal_word, guess):
    counter = 0
    x = 0
    new_list = []
    guess_string = get_string(guess)
    goal_word_string = get_string(goal_word)
    for i in range(len(guess_string)):
        if get_string(guess_string[i]) in goal_word_string and not guess_string[i] in new_list:
            counter += 1
            new_list += [guess_string[i]]
    return counter
