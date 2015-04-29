def numeral(num):
    outputlist=[]
    if type(num) != type(42):
        raise TypeError("You must input a valid num")
    if num>3000:
        raise ValueError("Your number must be 3000 or less")
    stringl=list(str(num))
    if len(stringl)==4:
        while num>999:
            outputlist.append("M")
            num=num-1000
        stringl.remove(stringl[0])
    if len(stringl)==3 and stringl[0]=="9":
        outputlist.append("C")
        outputlist.append("M")
        num=num-900
        stringl.remove(stringl[0])
    elif len(stringl)==3 and stringl[0]=="4":
        outputlist.append("C")
        outputlist.append("D")
        num=num-400
        stringl.remove(stringl[0])
    elif len(stringl)==3 and int(stringl[0])>4:
        outputlist.append("D")
        num=num-500
    if len(stringl)==3:
        while num>99:
            outputlist.append("C")
            num=num-100
        stringl.remove(stringl[0])
    if len(stringl)==2 and stringl[0]=="9":
        outputlist.append("X")
        outputlist.append("C")
        num=num-90
    elif len(stringl)==2 and stringl[0]=="4":
        outputlist.append("X")
        outputlist.append("L")
        num=num-40
        stringl.remove(stringl[0])
    elif len(stringl)==2 and int(stringl[0])>4:
        outputlist.append("L")
        num=num-50
    if len(stringl)==2:
        while num>9:
            outputlist.append("X")
            num=num-10
        stringl.remove(stringl[0])
    if stringl[0]=="9":
        outputlist.append("I")
        outputlist.append("X")
        num=num-9
        stringl.remove(stringl[0])
    elif stringl[0]=="4":
        outputlist.append("I")
        outputlist.append("V")
        num=num-4
        stringl.remove(stringl[0])
    elif int(stringl[0])>4:
        outputlist.append("V")
        num=num-5
    while num>0:
        outputlist.append("I")
        num=num-1
    outputlist="".join(outputlist)
    return outputlist


    
        
