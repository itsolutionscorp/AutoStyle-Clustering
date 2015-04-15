def slices(str,n):
    j = 0
    list = []

    if n > len(str) or n == 0:
        raise ValueError("Invalid combination") 

    list2 = [int(i) for i in str]

    while j + n <= len(list2):
        list.append(list2[j:j + n])
        j += 1
    return list
