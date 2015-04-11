def sum_of_multiples(num,factors = [3,5]):
    "if a second argument isn't passed it defaults to the list [3,5]"
    
    myList = []
    for i in range(num):
        for factor in factors:
            if factor == 0:
                myList.append(0)
            elif i%factor == 0 and i>0:
                myList.append(i)

    return (sum(set(myList)))

print(sum_of_multiples(4))
