def num_common_letters(goal_word, guess):
    total_similar = 0
    repeated = []
    for guess_letter in guess:
        for goal_letter in goal_word :
            if goal_letter == guess_letter and guess_letter not in repeated:
                    repeated += [guess_letter]
                    total_similar +=1
    return total_similar
            
