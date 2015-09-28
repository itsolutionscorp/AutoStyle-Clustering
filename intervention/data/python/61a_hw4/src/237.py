def num_common_letters(goal_word, guess):
    def remover(lst, index):
        return lst[:index] + lst[index + 1:]
    goalword = get_list(goal_word)
    guesslist = get_list(guess)
    for goalindex in range(len(goalword)):
        for index in range(len(guesslist)):
            if guesslist[index] == goalword[goalindex]:
                goal_word = remover(goalword, goalindex)
                guess = remover(guesslist, index)
                return 1 + num_common_letters(goal_word, guess)
    return 0
