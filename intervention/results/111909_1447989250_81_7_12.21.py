def num_common_letters(goal_word, guess):
    goal_word = list(set([ord(i) for i in goal_word]))
    guess = list(set([ord(i) for i in guess]))
    total = 0
    print(goal_word, guess)
    for i in goal_word:
        for j in guess:
            if i == j:
                total += 1
    return total

Positive Hints


Negative Hints


Approach Hints
Consider this approach: for each letter in the first word, think about how can you find whether it is also in the second word. To find whether it is also in the second word, you should only have to use a single line of code. Use the skeleton we've provided if you're stuck.

Skeleton
def num_common_letters(---------, -----):
    --------- = -
    --------- = []
    for - in list(---------):
        if - in list(-----) --- - --- in ---------:
            --------- += - 
            --------- += -
    return ---------    
