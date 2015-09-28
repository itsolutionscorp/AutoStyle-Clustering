def num_common_letters(goal_word, guess):
    goal_string = get_string(goal_word)
    guess_string = get_string(guess)
    goal_list = get_list(goal_word)
    guess_list = get_list(guess)
    goal_scores = [None]*26
    for k in range(26):
        goal_scores[k] = 0
    for i in range(len(goal_list)):
        for j in range(26):
            if letters[j] == goal_list[i]:
                goal_scores[j] = goal_scores[j] + 1
    guess_scores = [None]*26
    for k in range(26):
        guess_scores[k] = 0
    for i in range(len(guess_list)):
        for j in range(26):
            if letters[j] == guess_list[i]:
                guess_scores[j] = guess_scores[j] + 1
                
    shared_letts = 0
    for i in range(26):
        if min(goal_scores[i],guess_scores[i]) != 0:
            shared_letts += 1
    return shared_letts
        
