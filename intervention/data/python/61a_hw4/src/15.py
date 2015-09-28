def num_common_letters(goal_word, guess):
    x = 0
    a = False
    for element in goal_word:
        for index in guess:
            if element == index and a != True:
                x += 1
                a = True
        a = False
    return x
        
    
