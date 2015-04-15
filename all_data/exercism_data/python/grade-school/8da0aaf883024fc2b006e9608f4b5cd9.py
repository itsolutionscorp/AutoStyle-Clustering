from collections import defaultdict

class School:

    def __init__(self, name):
        self.name = name
        self.store = StudentDatabase()

    def add(self, name, grade):
        self.store.add(Student(name, grade))

    def grade(self, grade):
        return set(
            student.name for student in self.store.students
            if student.grade == grade
        )

    def sort(self):
        unsorted_school = self.db
        sorted_school = { }
        for grade in sorted(unsorted_school.keys()):
            sorted_school[grade] = tuple(sorted(unsorted_school[grade]))
        return sorted_school

    @property
    def db(self):
        db = defaultdict(set)
        for student in self.store.students:
            db[student.grade].add(student.name)
        return db

class Student:

    def __init__(self, name, grade):
        self.name = name
        self.grade = grade

class StudentDatabase:

    def __init__(self):
        self.__students = [ ]

    def add(self, student):
        self.__students.append(student)

    @property
    def students(self):
        return tuple(self.__students)
