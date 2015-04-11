class School:

    def __init__(self, name):
        self._name = name
        self.db = {}

    def add(self, student_name, grade):
        if grade not in self.db:
            self.db[grade] = set()

        self.db[grade].add(student_name)

    def grade(self, grade_to_look_up):
            return self.db.get(grade_to_look_up, set())

    def sort(self):
        return {grade: tuple(sorted(student_names))
            for grade, student_names in self.db.items()}
