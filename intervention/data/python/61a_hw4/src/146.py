def num_common_letters(goal_word, guess):
    count = 0
    repetition_list = []
    for item in get_string(goal_word):
        for item0 in get_string(guess):
            if item == item0 and not item0 in repetition_list:
                repetition_list = repetition_list + [item0]
                count += 1
    return count
