def num_common_letters(goal_word, guess):
    if 'b' in ['b', 'c', 'd']:
        print('true')
    else:
        print('flse')
    count=0
    print(get_list(goal_word))
    print(get_list(guess))
    for gc in get_list(guess):
        print(type(gc))
        print('GC=',gc)
        if gc in get_list(goal_word):
            count=count+1
            print(count)
    return count
