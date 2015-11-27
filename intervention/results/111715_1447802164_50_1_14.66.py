def num_common_letters(goal_word, guess):
    holder = []
    holder2 = []
    count=0
    for i in range(len(goal_word)):
        if goal_word[i] not in holder: 
            holder.append(goal_word[i])
    for j in range(len(guess)):
        if guess[j] in holder and guess[j] not in holder2:
            holder2.append(guess[j])
            count +=1
    return count

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
