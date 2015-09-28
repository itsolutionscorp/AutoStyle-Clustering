def num_common_letters(goal_word, guess):
    number_of_common = 0
    goal_string = get_string(goal_word)
    guess_string = get_string(guess)
    reduce_repeated = ""
    for elem in guess_string:
        if elem not in reduce_repeated:
            reduce_repeated += elem
    for elem in reduce_repeated:
        if elem in goal_string:
            number_of_common += 1
    return number_of_common
