def num_common_letters(goal_word, guess):
    goal = get_string(goal_word)
    guess = get_string(guess)
    k = 0
    for i in range(len(guess)):
        if guess[i] in goal:
            if i == 0:
                k += 1
            elif guess[i] not in guess[0:i]:
                k += 1
            else:
                k = k
    return k
