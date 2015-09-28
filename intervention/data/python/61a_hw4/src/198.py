def num_common_letters(goal_word, guess):
    count = 0
    i = 0
    while i < len(guess):
        j = 0
        while j < len(goal_word):
            if guess[i] == goal_word[j]:
                count += 1
                goal_word = goal_word[0:j]+goal_word[j+1:len(goal_word)]
            else:
                count += 0
            j+=1
        i+=1
    return count
