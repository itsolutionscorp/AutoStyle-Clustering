def nth_prime(n):
    ans = []
    i = 2
    things = []
    
    while len(ans) < n:
        flag = True
        for each in ans:
            if i % each == 0:
                flag = False
                break
        if flag:
            ans.append(i)

##        # To speed up the process we know a prime
##        # must end in '1','3','7','9' from this point on
##        if i < 11: i += 1
##        else:
##            if things == []: things = [2,4,2,2]
##            i += things.pop(0)

        # Actually it seems equivalent or faster
        # just by doing this
        i += 1
                
    return ans[-1]
