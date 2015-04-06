def num_common_letters(goal_word, guess):
    a=get_list(goal_word)
    b=get_list(guess)
    i=0
    num=0
    while i<len(a):
        j=0
        while j<len(b):
            if a[i]==b[j]:
                k=0
                judge=0
                while k<j:
                    if b[k]==b[j]:
                        judge=1
                    k=k+1
                if judge==0:
                    num=num+1
            j=j+1
        i=i+1
    return num
