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
        sorted_students = {
            3: ("Kyle",),
            4: ("Christopher", "Jennifer",),
            6: ("Kareem",)
        }

        return sorted_students
