class School(object):

    db = {};

    def __init__(self, name):
        self.schoolName = name;
        self.db = {};

    def grade(self, grade):
        if grade in self.db:
            return self.db[grade];
        else:
            return set();

    def add(self, name, grade=None):
        self.newStudent = Student();
        self.newStudent.set_name(name);
        if grade is None:
            grade = -1;
        self.newStudent.set_grade(grade);
        if not self.db or grade not in self.db:
            self.db[grade] = set();
            self.db[grade].add(self.newStudent.get_name());
        else:
            ## add to set of the corresponding grade
            self.db[grade].add(self.newStudent.get_name());
            ## gradesDB is a dictionary that contains list elements for each different key
            ## self.db[grade].append(self.newStudent.get_name());

    def sort(self):
        return {
            grade: tuple(names)
            for grade, names in sorted(self.db.iteritems())
        }


class Student(object):

    def __init__(self):

        self.studentName = "";
        self.studentGrade = -1;

    def set_name(self, name):
        self.studentName = name;

    def set_grade(self, grade):
        self.studentGrade = grade;

    def get_name(self):
        return self.studentName;

    def get_grade(self):
        return self.studentGrade;
