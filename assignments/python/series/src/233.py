def slices(string, length):
    test=True
    output_list=[]
    counter=0
    relen=length-1
    if len(string)<length or length<1:
        raise ValueError("YOur string must be longer then your length")
    while test==True:
        listthing=[]
        while relen>-1:
            listthing.append(string[relen])
        try:
            output_list.append(listthing)
            counter=counter+1
        except:
            break
    return output_list
