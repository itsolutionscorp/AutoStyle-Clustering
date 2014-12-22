def num_common_letters(goal_word, guess):
    common_letters = 0
    list_goal = get_list(goal_word)
    list_guess = get_list(guess)
    guesslist_norepeats = [] #creating yet another list of guess but without repeats
    for l in list_guess:
        if l not in guesslist_norepeats:
            guesslist_norepeats.append(l)
    for character in list_goal:
        if character in guesslist_norepeats:
            common_letters += 1 
    return common_letters
