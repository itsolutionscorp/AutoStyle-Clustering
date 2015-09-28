def num_common_letters(goal_word, guess):
    in_common = 0 
    character_list = []
    for i in guess[0][:]:
        if i not in character_list: 
            character_list.append(i)
    for i in character_list: 
        if i in goal_word[0][:]: 
            in_common += 1 
    return in_common
