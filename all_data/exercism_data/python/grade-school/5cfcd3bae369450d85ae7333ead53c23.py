class School:

    def __init__(self, name):
        self.name=name
        self.db={}
        

    def add(self, kid, grade):
        try:
            self.db[grade]=self.db[grade] | {kid}
        except:
            self.db[grade]={kid}

    def grade(self, grade_to_find):
        try:
            return self.db[grade_to_find]
        except:
            return set()

    def sort(self):
        output={}
        for grad in self.db:
            kids=self.db[grad]
            output[grad]=tuple(kids)
        return output
            

    

    
