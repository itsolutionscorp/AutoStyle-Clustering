def num_common_letters(goal_word, guess):
    goal_word = get_list(goal_word)
    guess = get_list(guess)
    index = 0
    total = 0
    for index in range(len(goal_word)):
        index2 = 0
        for index2 in range(len(guess)):
            if goal_word[index] == guess[index2]:
                total += 1
                break
            else:
                index2 += 1
        index += 1
    return total
