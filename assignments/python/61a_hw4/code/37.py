def num_common_letters(goal_word, guess):
    goal_word1, guess1 = get_list(goal_word), get_list(guess)
    if len(goal_word1) == len(guess1):    
        return len(set(goal_word1).intersection(guess1))
    return bad_num_letters
