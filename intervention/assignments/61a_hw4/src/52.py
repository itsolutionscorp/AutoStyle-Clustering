def num_common_letters(goal_word, guess):
    goal, guessed = get_list(goal_word), get_list(guess)
    counter = 0
    for el in goal:
        for elem in guessed:
            if el == elem:
                counter += 1
                break
    return counter
