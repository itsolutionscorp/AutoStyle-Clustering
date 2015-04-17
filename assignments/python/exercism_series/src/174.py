def slices(string, length):
    test=True
    output_list=[]
    counter=0
    relen=length-1
    if len(string)<length or length<1:
        raise ValueError("Your string must be longer then your length")
    x=len(string)-length
    while x>-1:
        set_of_numbers=[]
        while relen>-1:
            try:
                set_of_numbers.append(int(string[relen+counter]))
            except:
                raise ValueError("Your string must only contain numbers!")
            relen=relen-1
        set_of_numbers.reverse()
        output_list.append(set_of_numbers)
        relen=length-1
        counter=counter+1
        x=x-1
    return output_list
