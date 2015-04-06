def num_common_letters(goal_word, guess):
    goal=get_string(goal_word)
    guess=get_string(guess)
    n,i=0,0
    while i<=len(goal)-1:
        j=0
        while j<=len(guess)-1:
            if goal[i]==guess[j]:
                n+=1
                j=len(guess)
            else:
                j+=1
        i+=1
    return n
