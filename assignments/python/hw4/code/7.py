def num_common_letters(goal_word, guess):
    goal_word_lst = get_list(goal_word)
    for i in range(len(goal_word_lst)):
        if i+1 not in range(len(goal_word_lst)):
            pass
        if goal_word_lst[i] in goal_word_lst[i+1:]:
            return "This isn't a superhero movie franchise. No repeats."
    guess_lst_unique = get_list(''.join(set(get_string(guess))) )   
    total_same = 0
    for x in range(len(guess_lst_unique)):
        for y in range(len(goal_word_lst)):
            if guess_lst_unique[x] == goal_word_lst[y]:
                total_same +=1
    return total_same
