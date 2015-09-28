def num_common_letters(goal_word, guess):
    total = []
    for el in get_list(guess):
        for item in get_list(goal_word):
            if el == item and not el in total: 
                total = [el] + total          
    return len(total)
      
        
