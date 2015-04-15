

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
        sorted_db = dict()
        for x,y in self.db.iteritems():
            sorted_db[x] = ()
            for t in sorted(y):
                sorted_db[x] += (t,)
        return sorted_db
            
            
        
