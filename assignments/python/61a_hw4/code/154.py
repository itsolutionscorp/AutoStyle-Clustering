def num_common_letters(goal_word, guess):
    word1 = get_list(goal_word)
    word2 = get_list(guess)
    common = 0
    i = 0
    for letter in word1:
        if letter in word2:
            common +=1
    return common
