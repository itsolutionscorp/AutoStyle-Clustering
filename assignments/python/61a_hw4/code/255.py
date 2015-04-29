def num_common_letters(goal_word, guess):
    count = 0
    result = []
    for letter in get_list(guess):
        if letter in get_list(goal_word) and letter not in result:
            count += 1
            result = result + [letter]
    return count
        
