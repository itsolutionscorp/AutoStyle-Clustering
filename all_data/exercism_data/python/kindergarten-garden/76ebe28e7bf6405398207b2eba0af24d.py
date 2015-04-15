class Garden:
    def __init__(self, row_of_plants, students=["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]):
        row_list=row_of_plants.split("\n")
        row1=row_list[0]
        row2=row_list[1]
        self.students=sorted(students)
        self.row1_full=[]
        self.row2_full=[]
        cupnum=0
        self.kidtoplant={}
        self.plants_types=["Violets", "Radishes", "Clover", "Grass"]
        plantdict={"V":"Violets", "R":"Radishes", "C":"Clover", "G":"Grass"}
        if len(row1)!=len(row2):
            raise ValueError("You don't have an equal number of cups in each row.  Now all the children are crying.  Look at what you have done.  You made kindergardeners cry, great job.  ):")
        for letter in row1:
            try:
                self.row1_full.append(plantdict[letter.upper()])
            except:
                raise ValueError("Your garden has weeds of other plant types, they have overcome your garden and destroyed the world!")
        for letter in row2:
            try:
                self.row2_full.append(plantdict[letter.upper()])
            except:
                raise ValueError("Your garden has weeds of other plant types, they have become sentient beings and walked away!")
        for kid in self.students:
            try:
                self.kidtoplant[kid]=[self.row1_full[cupnum], self.row1_full[cupnum+1], self.row2_full[cupnum], self.row2_full[cupnum+1]]
                cupnum=cupnum+2
            except:
                pass

    
    def plants(self, kid):
        try:
            return self.kidtoplant[kid]
        except:
            raise ValueError("That kid is not in your class. You have been fired for being such a terrible teacher!")
    
