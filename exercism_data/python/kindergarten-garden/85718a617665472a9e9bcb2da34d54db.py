class Garden:
    # Default children and plants
    CHILDREN = ["Alice", "Bob", "Charlie", "David",
                "Eve", "Fred", "Ginny", "Harriet",
                "Ileana", "Joseph", "Kincaid", "Larry"]
    PLANTS = {"V": "Violets", "C": "Clover", "R": "Radishes", "G": "Grass"}
    
    def __init__(self, plants, students = CHILDREN):
        # Initialize class variables
        rows = plants.split("\n")           
        self.students = sorted(students)
        self.row1 = rows[0]
        self.row2 = rows[1]
        self.children = {}
        
        # Initialize an empty list for each child to hold their plants
        for child in self.students:
            self.children[child] = []

        # Iterate over the first plant row
        child = -1              # index of children in student list
        for i in range(len(self.row1)):
            if i % 2 == 0:
                child += 1
                plants = []
            self.children[self.students[child]] += [Garden.PLANTS[self.row1[i]]]

        # Iterate over the second plant row
        child = -1              # 
        for i in range(len(self.row2)):
            if i % 2 == 0:
                child += 1
                plants = []
            self.children[self.students[child]] += [Garden.PLANTS[self.row2[i]]]

    # Return a list of plants by child name
    def plants(self, child):
        return self.children[child]

            
