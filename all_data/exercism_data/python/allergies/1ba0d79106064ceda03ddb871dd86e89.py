def Allergies(score):
    scoreG=score
    alargens={1:"eggs",2:"peanuts",4:"shellfish",8:"strawberries",16:"tomatoes",32:"chocolate",64:"pollen",128:"cats"}
    leftPoints=score
    allergies=[]
    start=128
    
    while(start >= 1):
        if(leftPoints >= start):
            leftPoints-=start
            allergies.append(alargens[start])
        
        start=start/2
    
    allergies.reverse()
    return allergies

    def is_allergic_to(allergy):
        for c in allergies:
            if(c==allergies):
                return True
    
        return False
        
print Allergies(1).is_allergic_to('eggs')
