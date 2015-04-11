import itertools

"""
1. There are five houses.	              
2. The Englishman lives in the red house. 
3. The Spaniard owns the dog.	          
4. coffee is drunk in the green house.    
5. The Ukrainian drinks tea.	          
6. The green house is immediately to the right of the ivory house.	/dependent on color
7. The Old Gold smoker owns snails.		    
8. Kools are smoked in the yellow house.	
9. milk is drunk in the middle house.		
10. The Norwegian lives in the first house.	
11. The man who smokes Chesterfields lives in the house next to the man with the fox.
12. Kools are smoked in the house next to the house where the horse is kept.
13. The Lucky Strike smoker drinks orange juice juice.
14. The Japanese smokes Parliaments.			
15. The Norwegian lives next to the blue house.	

Depedencies:
1.  house num                   /first (derived from results)
6.  color                       /second independent of other attributes
2.  color or nation             /third  dependent on house num or color
10. house num or nation         /third  "  "
15. color or nation             /third  "  "
4.  color or drink              /fourth dependent on house num or color or nation
5.  nation or drink             /fourth "  " 
9.  house num or drink          /fourth "  "
8.  color or cig                /fifth  dependent on house num, color, nation, or drink
13. drink or cig                /fifth  "  "
14. nation or cig               /fifth  "  "
3.  nation or pet               /last   dependent on any of the previous
7.  cig or pet                  /last   "  "
11. house num or cig or pet     /last   "  "
12. house num or cig or pet     /last   "  "
"""

def geti(perms, value):
    """ Visually cleaner way to translate string to index value """
    return perms.index(value)
    
def display_perms(perms):
    """ Used to view permutations along the way to troubleshoot """
    for results in perms:
        for _type in results:
            for item in _type:
                print "{0:15}".format(item),
            print 
        print '+{0}+'.format('-'*70)

def solve():
    #  6. The green house is immediately to the right of the ivory house.
    #  Started here as this was the only rule independant of other attributes
    colorperms = list(itertools.permutations(
            ['red','green','ivory','yellow','blue']))
    permutations = []
    
    for colors in colorperms:
        if geti(colors,'green') - geti(colors,'ivory') is 1:
            permutations.append(colors)
    colorperms = list(permutations)
    
    #  2. The Englishman lives in the red house.
    # 10. The Norwegian lives in the first house.
    # 15. The Norwegian lives next to the blue house.
    nationperms = list(itertools.permutations(
            ['Englishman','Spaniard','Ukranian','Norwegian','Japanese']))
    perms = []
    for colors in colorperms:
        for nations in nationperms:
            if geti(nations,'Englishman') == geti(colors,'red') and \
                nations[0] == 'Norwegian' and \
                abs(geti(nations,'Norwegian') - geti(colors,'blue')) == 1:
                   perms.append((colors,nations))
    
    temp = []
    #  4. coffee is drunk in the green house. 
    #  5. The Ukrainian drinks tea.	       
    #  9. milk is drunk in the middle house.
    drinkperms = list(itertools.permutations(
            ['coffee','tea','milk','orange juice','water']))
    for perm in perms:
        colors = perm[0]
        nations = perm[1]
        for drinks in drinkperms:
            if geti(drinks,'coffee') == geti(colors,'green') and \
                geti(drinks,'tea') == geti(nations,'Ukranian') and \
                geti(drinks,'milk') == 2:
                    temp.append((colors,nations,drinks))
                    
                    
                    
    perms = list(temp)
    temp = []     
    #  8. Kools are smoked in the yellow house.	            
    # 13. The Lucky Strike smoker drinks orange juice juice.            
    # 14. The Japanese smokes Parliaments.			            
    cigperms = list(itertools.permutations(
            ['Old Gold','Kools','Chesterfields','Lucky Strike','Parliaments']))
    for perm in perms:
        colors = perm[0]
        nations = perm[1]
        drinks = perm[2]
        for cigs in cigperms:
            if geti(cigs,'Kools') == geti(colors,'yellow') and \
                geti(cigs,'Lucky Strike') == geti(drinks,'orange juice') and \
                geti(cigs,'Parliaments') == geti(nations,'Japanese'):
                    temp.append((colors,nations,drinks,cigs))
                    
                    
    perms = list(temp)
    temp = []
    #  3. The Spaniard owns the dog.	  
    #  7. The Old Gold smoker owns snails.	
    # 11. The man who smokes Chesterfields lives in the house next to the man with the fox.
    # 12. Kools are smoked in the house next to the house where the horse is kept.	
    petperms = list(itertools.permutations(['dog','snails','fox','horse','zebra']))
    for perm in perms:
        colors = perm[0]
        nations = perm[1]
        drinks = perm[2]
        cigs = perm[3]
        for pets in petperms:
            if geti(pets,'dog') == geti(nations,'Spaniard') and \
                geti(pets,'snails') == geti(cigs,'Old Gold') and \
                abs(geti(pets,'fox') - geti(cigs,'Chesterfields')) == 1 and \
                abs(geti(pets,'horse') - geti(cigs,'Kools')) == 1:
                    temp.append((colors,nations,drinks,cigs,pets))
    
    # Return the only remaining element
    return list(temp)[0]

def solution():
    perms = solve()
    return  "It is the {} who drinks the water.\nThe {} keeps the zebra.".format(
    perms[1][perms[2].index('water')], perms[1][perms[4].index('zebra')])
    
print solution()
