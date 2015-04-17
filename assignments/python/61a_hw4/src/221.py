def num_common_letters(goal_word, guess): 
    g_w, g = get_list(goal_word), get_list(guess)
    count = 0
    for letters in g_w:
        if  g.count(letters) >= 1:
            count += 1
    return count        
