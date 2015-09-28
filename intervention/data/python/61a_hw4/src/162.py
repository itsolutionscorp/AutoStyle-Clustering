def num_common_letters(goal_word, guess):
    goal_length = len(goal_word)
    guess_length = len(guess)
    temp_goal = list(goal_word)
    temp_guess = list(guess)
    indexer_guess = 0
    number_letters = 0
    while indexer_guess < guess_length:
        indexer_goal = 0
        while indexer_goal < goal_length and temp_goal[indexer_goal] != temp_guess[indexer_guess]:
            indexer_goal += 1
        if indexer_goal == goal_length:
            indexer_guess += 1
        else:
            temp_goal.remove(temp_goal[indexer_goal])
            temp_guess.remove(temp_guess[indexer_guess])
            number_letters = number_letters + 1
            goal_length -= 1
            guess_length -= 1            
    return number_letters
