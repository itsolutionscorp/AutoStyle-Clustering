def slices(what,step):
    
    if step > len(what) or step == 0:
        raise ValueError
    list_of_tupler = list()
    in_list= list()
    
    for i in range(len(what)+1):
        #checking if the remaining space is smaller than our step
        if step<=len(what) - (i-1):
            #nmb holds the sequence with len(step) each time starting from the next character
            nmb = what[i-1:step+i-1:]
            #here the string is broken down into individual integers for testing purposes
            for i in range(len(nmb)):
                in_list.append(int(nmb[i:i+1:]))

            
            list_of_tupler.append(in_list)
            in_list = []
    #i couldn't get rid of the empty list that was first element in the list_of_tupler list. Im guessing because i appended to an empty list.
    return list_of_tupler[1::]
