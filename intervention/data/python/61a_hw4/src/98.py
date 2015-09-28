def num_common_letters(goal_word, guess):
    goalsword = get_string(goal_word)
    gsword = get_string(guess)
    num = 0
    for elem in goalsword:
        if elem in gsword:
            num+=1
    return num
