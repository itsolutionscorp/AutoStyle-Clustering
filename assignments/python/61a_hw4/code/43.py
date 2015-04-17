def num_common_letters(goal_word, guess):
    x = get_list(goal_word)
    y = get_list(guess)
    b, final_answer = 0,0
    while b < len(x):
        if x[b] in y:
            final_answer += 1
        b += 1
    return final_answer
