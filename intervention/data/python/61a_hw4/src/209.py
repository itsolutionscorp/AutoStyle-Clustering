def num_common_letters(goal_word, guess):
    goal_word = get_list(goal_word)
    guess = list(set(get_list(guess))) # Remove duplicates
    total = 0
    for i in range(len(goal_word)):
        for j in range(len(guess)):
            if goal_word[i] == guess[j]:
                total += 1
    return total
