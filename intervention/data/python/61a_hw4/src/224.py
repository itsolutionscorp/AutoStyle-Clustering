def num_common_letters(goal_word, guess):
    commonLetters = 0
    distinct = True
    for i in range(len(goal_word)):
        distinct = True
        for j in range(len(guess)):
            if goal_word[i]==guess[j] and distinct == True:
                commonLetters += 1
                distinct=False
    return commonLetters
