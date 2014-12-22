def num_common_letters(goal_word, guess):
    a,b,c,letter_count=get_list(goal_word),get_list(guess),0,0
    while c<=len(a)-1:
        d=0
        while d<=len(a)-1:
            if a[c]==b[d]:
                letter_count+=1
                d=d+len(a)
            else:
                d+=1
        c+=1
    return letter_count
