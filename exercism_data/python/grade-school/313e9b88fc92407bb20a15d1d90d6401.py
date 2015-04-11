class School():
    
    # Initialize database using dictionary data structure
    def __init__(self, schoolname):
        self.school = schoolname
        self.db = dict()
        
    
    def add(self, name, grade):
        if not grade in self.db:
            self.db[grade] = {name}
        else:
            self.db[grade].add(name)
            
    def grade(self, grade):
        if not grade in self.db:
            return set()
        return self.db[grade]
    
    # Iterate over db to add sorted tuples of names to each grade
    def sort(self):
        sortedstudents = dict()
        
        for grade in sorted(self.db):
            sortedstudents[grade] = ()
            for name in sorted(self.db[grade]):
                sortedstudents[grade] += (name,)
                
        return sortedstudents
