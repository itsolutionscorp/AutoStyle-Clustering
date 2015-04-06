def num_common_letters(goal_word, guess):
    goal_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    count,index_goal,index_guess = 0,0,0
    while index_goal < len(goal_lst):
        index_guess = 0
        while index_guess < len(guess_lst):
            if goal_lst[index_goal] == guess_lst[index_guess]:
                k = 0
                judge = 0
                while k < index_guess:
                    if guess_lst[k] == guess_lst[index_guess]:
                        judge = 1
                    k += 1
                if judge == 0:
                    count += 1
            index_guess += 1
        index_goal += 1
    return count
