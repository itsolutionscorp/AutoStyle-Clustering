def num_common_letters(goal_word, guess):
    # remove duplicate characters from both
    goal_word = set(goal_word)
    guess = set(guess)

    return len(set.union(goal_word, guess))

