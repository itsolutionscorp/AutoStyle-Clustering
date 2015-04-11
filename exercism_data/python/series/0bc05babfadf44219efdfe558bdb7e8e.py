__author__ = 'jeffmarkey'

def slices(values, length):

    if(length > len(values) or (length <=0)):
        raise ValueError

    list = []
    sub_list = []

    for content in range(0,max(length,len(values))):
        if ((content+length)<=len(values)):
            for elm in values[content:content+length]:
                sub_list.append(int(elm))
            list.append(sub_list)
            sub_list = []

    return list
