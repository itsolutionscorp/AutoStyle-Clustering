class Allergies(object):
    def __init__(self,x):
        self.x=x
        count=self.x
        allergies_dic2 ={1:'eggs',2:'peanuts',4:'shellfish',
                        8:'strawberries',16:'tomatoes',
                        32:'chocolate',64:'pollen',128:'cats'}
        sorted_list = allergies_dic2.keys()
        sorted_list.sort(reverse=True)
        al_str=''
        for n in sorted_list:
            if n==count:
                al_str += ' ' + allergies_dic2[n]
                break
            elif n > count:
                continue
            else :
                al_str += ' ' + allergies_dic2[n]
                count -=n
        al_str=al_str.strip()
        self.list = al_str.split()

    def is_allergic_to(self,kill):
        return (kill in self.list)



    
           

    

    

    
