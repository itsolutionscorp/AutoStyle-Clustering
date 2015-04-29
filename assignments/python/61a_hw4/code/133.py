def num_common_letters(goal_word, guess):
    seen_it = False
    counter = 0
    goal_word = get_list(goal_word)
    guess = get_list(guess)
    guess_refresh = guess
    while goal_word != []:
        guess = guess_refresh
        seen_it = False
        while guess != []:
            if guess[0] == goal_word[0]:
                if seen_it == False:
                    counter += 1 
                    seen_it = True
                guess = guess[1:]
            else:
                guess = guess[1:]
        goal_word = goal_word[1:]
    return counter
