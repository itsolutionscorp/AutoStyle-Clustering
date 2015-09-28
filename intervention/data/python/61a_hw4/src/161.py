def num_common_letters(goal_word, guess):
    result = []
    result_len = 0
    goal_word_list = []
    guess_word_list = []
    goal_word_list += get_list(goal_word)
    guess_word_list += get_list(guess)
    for i in range(len(goal_word_list)):
        for e in range(len(guess_word_list)):
            if goal_word_list[i] == guess_word_list[e]:
                result += [goal_word_list[i]]
    result_len = len(result)
    k = 0
    while k <= len(result)-1:
        if result[k] in result [k+1:] + result[:k]:
            result_len -= 1
            k += 1
        k += 1
    return result_len
