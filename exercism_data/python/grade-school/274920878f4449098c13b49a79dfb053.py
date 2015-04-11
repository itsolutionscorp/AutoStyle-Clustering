

class School(object):


    def __init__(self, school_name):
        self.school_name = school_name
        self.db = {} 
        
        
        
        
    def add (self,name, grade):
        if grade in self.db:
            self.db[grade].add(name)
        else:
            self.db[grade] = {name}
    
    def grade (self,grd):
        if grd in self.db:
            return self.db[grd]
        else:
            return set()
        
    def sort (self):
        for x,y in self.db.iteritems():
            self.db[x] = set(sorted(y))
        return self.db
            
            
        
