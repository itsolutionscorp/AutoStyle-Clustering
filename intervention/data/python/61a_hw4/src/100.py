def num_common_letters(goal_word, guess):
    counter = 0
    fullguess = get_list(guess)
    guess = []
    goal = get_list(goal_word)
    for a in range(len(fullguess)):
        if fullguess[a] not in guess:
            guess += fullguess[a]
    for a in range(len(guess)):
        for b in range(len(goal)):
            if goal[b] == guess[a]:
                counter += 1 
    return counter
