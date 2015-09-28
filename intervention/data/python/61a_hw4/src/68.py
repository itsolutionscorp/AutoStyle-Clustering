def num_common_letters(goal_word, guess):
    goal_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    x, guessed = 0, []
    for i in range(len(guess_lst)):
        if guessed.count(guess_lst[i]): continue
        elif goal_lst.count(guess_lst[i]):
             x += 1
             guessed += guess_lst[i]
    return x
