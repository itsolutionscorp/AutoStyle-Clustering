def num_common_letter(goal_word, guess):
    if len(guess) == 0 or len(goal_word) == 0:
        return 0
    elif guess[-1] in set(goal_word):
        set(goal_word).remove(guess[-1])
        return 1 + num_common_letter(set(goal_word), guess[:-1])
    else:
        return num_common_letter(goal_word, guess[:-1])


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
