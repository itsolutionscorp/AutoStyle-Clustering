def num_common_letters(goal_word, guess):
    gs_w = get_list(goal_word)
    gs = get_list(guess)
    num_similr = 0
    i = 0
    while i < len(guess):
    	if gs_w[i] in gs:
    		num_similr += 1
    	i += 1
    return num_similr
