class School(object):

    def __init__(self, name):
        self.school_name=name
        self.__db = {}

    @property
    def db(self):
        return dict(self.__db)


    def add(self, name, grade):
        if grade in self.__db:
            self.__db[grade].add(name)
            
        else:
            self.__db[grade] = set([name])

    def grade(self, grade):
        return (self.__db[grade]) if grade in self.__db else set([])
    


    def sort(self):
        results_dict = {}
        for entry in self.__db:
           results_dict[entry] = tuple(sorted(self.__db[entry]))
        return results_dict

        
