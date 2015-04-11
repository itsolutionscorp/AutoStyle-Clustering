class Garden:

    students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']
    plants_list = ['Grass', 'Clover', 'Radishes', 'Violets']

    def __init__(self, garden_string, students=None):
        if students:
            self.students = sorted(students)
        self.plants_dict = self._make_plants_dict(self._make_garden(garden_string))

    def plants(self, student):
        return [self._name_plant(initial) for initial in self.plants_dict[student]]

    def _make_garden(self, garden_string):
        return garden_string.split('\n')

    def _make_plants_dict(self, garden):
        plants_dict = {}
        for i in range(0, len(self.students)):
            plants_dict[self.students[i]] = garden[0][i*2:(i*2)+2] + garden[1][i*2:(i*2)+2]
        return plants_dict

    def _name_plant(self, initial):
        for plant in self.plants_list:
            if plant.startswith(initial):
                return plant
