available_plants = {'G': "Grass",
                    'C': "Clover",
                    'R': "Radishes",
                    'V': "Violets"}

class Garden():

    def __init__(self, plan, students=("Alice Bob Charlie David \
        Eve Fred Ginny Harriet \
        Ileana Joseph Kincaid Larry ".split())):
        self.plans = plan.split('\n')
        self.students = sorted(students)

    def plants(self, student):
        student_index = self.students.index(student) * 2
        her_plants = [p for p in self.plans[0][student_index:(student_index + 2)]]
        her_plants += [p for p in self.plans[1][student_index:(student_index + 2)]]
        return [available_plants[x] for x in her_plants]
