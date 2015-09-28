def num_common_letters(goal_word, guess):
    goalList = get_list(goal_word)
    guessList = get_list(guess)
    commonSet = set()
    for i in range(len(guessList)):
        if guessList[i] in goalList:
            commonSet.add(guessList[i])
    return len(commonSet)
