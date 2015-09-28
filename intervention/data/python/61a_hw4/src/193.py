def num_common_letters(goal_word, guess):
    goal = get_list(goal_word)
    guess = get_list(guess)
    count = []
    for i in range(len(goal)):
        if (guess[i] in goal) and (guess[i] not in count):
            count += guess[i]
    return len(count)
