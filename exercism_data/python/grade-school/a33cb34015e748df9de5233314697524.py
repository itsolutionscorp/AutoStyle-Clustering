class School:

    def __init__(self, school_name):
        self.school_name = school_name
        self.dat = Database()

    def add(self, student_name, grade):
        self.dat.add(student_name, grade)
        return

    def grade(self, grade):
        return self.dat.grade(grade)

    def sort(self):
        return {x: tuple(self.dat.student_data[x]) for x in sorted(self.dat.student_data)}

    @property #tried to make db read only using this little trick, don't see the point though
    def db(self):
        return self.dat.get_database()

class Database(School):

    def __init__(self):
        self.student_data = {}

    def add(self, student_name, grade):
        try:         
            self.student_data[grade].update({student_name})
        except KeyError:
            self.student_data[grade] = {student_name}
        return

    def grade(self, grade):
        try:        
            return self.student_data[grade]
        except KeyError:
            return set([])
    
    def get_database(self):
        return self.student_data.copy()
