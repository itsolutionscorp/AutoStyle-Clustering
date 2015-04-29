class School():
    def __init__(self, school_name):
        self.db = {}
    

    def add(self, student, grade):
        if grade in self.db:
           self.db[grade].add(student)
        else:
            self.db[grade] = set([student])


    def grade(self, grade):
        return self.db.get(grade) if grade in self.db else set([])

    
    def sort(self):
        grades = sorted(self.db)
        sorted_db = []
        # for value in grades:
        #    sorted_db.append(grades[value])
        
        return sorted(self.db.items())
