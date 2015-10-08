def num_common_letters(goal_word, guess):
    length=len(get_string(goal_word))
    letters_seen=[]
    for el in letters:
        for i in range(length):
            if el in get_string(guess)[i] and el not in letters_seen:
                letters_seen=letters_seen+[get_string(guess)[i]]
    num=0
    for i in range(len(letters_seen)):
        if letters_seen[i] in get_string(goal_word):
            num+=1
        else:
            num=num
    return num
