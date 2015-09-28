def num_common_letters(goal_word, guess):
    list_of_goal_word = []
    result = 0
    for i in goal_word:
        for l in guess: 
            if i == l:
                result += 1 
                list_of_goal_word += i
                if list_of_goal_word.count(i) > 1:
                    result -= 1
    return result
