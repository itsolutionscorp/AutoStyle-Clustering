import itertools
class Garden(object):
    
    list_of_names = ['Alice','Bob','Charlie','David','Eve','Fred','Ginny','Harriet','Ileana','Joseph','Kincaid','Larry']
    #i guess all this code in init is not wanted. What's a more elegant way to do it?
    def __init__(self,planting_order,students = list_of_names):
        self.list_of_names = students
        self.list_of_names.sort()
        #list_of_names holds the names of the students in alphabetical order
        list_of_plants = [i for i in planting_order]
        list_of_plants.remove('\n')
        #list_of_plants holds the plants
        list_of_plants_in_order = []
        #half_range is needed for the calculations
        half_range = len(list_of_plants)//2
        #the for loops runs once for each group of 4 
        for i in range(half_range//2):
            #it just get's two from the start of each list(before and after \n)
            four_plants = [list_of_plants[2*i:(2*i)+2:],list_of_plants[2*i+half_range:2*i+2+half_range:]]
            list_of_plants_in_order.append(four_plants)
        self.ready_to_play = []
        #a for loop to get in the nested list and replace the acronyms for the full names
        for i in list_of_plants_in_order:
            for j in i:
                for e in j:
                    if e == 'R':
                        self.ready_to_play.append('Radishes')
                    if e == 'C':
                        self.ready_to_play.append('Clover')
                    if e == 'V':
                        self.ready_to_play.append('Violets')
                    if e == 'G':
                        self.ready_to_play.append('Grass')
    
    def plants(self,what):
        for counter,name in enumerate(self.list_of_names):
            if name == what:
                return self.ready_to_play[counter*4:counter*4+4]
        return self.ready_to_play

            
        return 0


    
