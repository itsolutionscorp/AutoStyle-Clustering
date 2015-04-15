def num_common_letters(goal_word, guess):
    counter = 0
    goal_word = get_list(goal_word) 
    guess = get_list(guess)
    for i in goal_word:
        for j in guess:
            if i == j:
                counter += 1
                guess.remove(j)
    return counter
