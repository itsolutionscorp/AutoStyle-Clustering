def num_common_letters(goal_word, guess):
    sum = 0
    goal = get_list(goal_word)
    guess_word = get_list(guess)
    for i in range(len(goal)):
        tmp = goal[i]
        for j in range(len(guess_word)):
            if tmp == guess_word[j]:
                sum += 1
                break
    return sum
