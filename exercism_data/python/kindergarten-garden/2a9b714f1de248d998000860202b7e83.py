import collections


class Garden(object):
    list_of_students = [
        'Alice', 'Bob', 'Charlie', 'David', 'Eve',
        'Fred', 'Ginny', 'Harriet',
        'Ileana', 'Joseph', 'Kincaid', 'Larry'
    ]

    kinds_of_plants = {
        'G': 'Grass',
        'C': 'Clover',
        'R': 'Radishes',
        'V': 'Violets'
    }

    def __init__(self, diagram, students=list_of_students):
        self.diagram = diagram
        self.students = students

    def plants(self, student):
        students_and_plants_dict = collections.defaultdict(list)

        for i, pupil in enumerate(sorted(self.students)):
            for row in self.diagram.split('\n'):
                if i < len(row) / 2:
                    students_and_plants_dict[pupil].append(Garden.kinds_of_plants.get(row[i + i]))
                    students_and_plants_dict[pupil].append(Garden.kinds_of_plants.get(row[i + i + 1]))
        return students_and_plants_dict.get(student)
