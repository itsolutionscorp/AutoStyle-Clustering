def num_common_letters(goal_word, guess):
    k=0
    for i in goal_word:
        for j in guess:
            if i==j:
                k+=1
                break
    return k
