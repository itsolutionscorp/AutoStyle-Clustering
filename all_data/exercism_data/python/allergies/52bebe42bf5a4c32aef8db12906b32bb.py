def Allergies_str_to_int(al):
    allergies_dic ={'eggs':1,'peanuts':2,'shellfish':4,
                    'strawberries':8,'tomatoes':16,
                    'chocolate':32,'pollen':64,'cats':128}
    al_list=al.split()
    al_count=0
    for word in al_list:
        al_count+=allergies_dir[word]
    return al_count
    
def Allergies(num):
    allergies_dic2 ={1:'eggs',2:'peanuts',4:'shellfish',
                    8:'strawberries',16:'tomatoes',
                    32:'chocolate',64:'pollen',128:'cats'}
    sorted_list = allergies_dic2.keys()
    sorted_list.sort()
    al_str=''
    for n in sorted_list:
        if n>num:
            break
        elif n <=num:
            al_str += ' ' + allergies_dic2[n]
            num -=n
    return al_str
        
            
    
    
