from functools import partial

def grouper(iterable, n):
    return zip(*[iter(iterable)] * n)
def flatten(seq):
    for item in seq:
        if hasattr(item, '__iter__') and not isinstance(item, str):
            yield from flatten(item)
        else:
            yield item

class Garden(object):
    plant_names = {"G":"Grass",
                   "C":"Clover",
                   "R":"Radishes",
                   "V":"Violets"}
    def __init__(self, rows, students=None):
        self.rows = rows.splitlines()
        if students is None:
            students = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred",
                        "Ginny", "Harriet", "Ileana", "Joseph",
                        "Kincaid", "Larry"]
        self.students = sorted(students)
        self.plant_assignments = {student: list(plant_group) for student,plant_group
                in zip(self.students, self.plant_groups(self.rows))}
    def get_plant_name(self,plant_letter):
        try:
            return self.plant_names[plant_letter]
        except KeyError:
            raise ValueError("Plant letter {} is not defined".format(plant_letter))
    def plants(self,name):
        return self.plant_assignments[name]
    def plant_groups(self,rows):
        """Group each row in twos, then zip, flatten,
        and map in the expected way so:

        "VVGGCC\nVVGGCC"

        becomes:

        [('Violet','Violet','Violet','Violet'),
         ('Grass','Grass','Grass','Grass'),
         ('Clover','Clover','Clover','Clover')]"""
        grouper_by_two = partial(grouper, n=2)
        grouped_by_two = map(grouper_by_two, rows)
        cubewise = zip(*grouped_by_two)
        flat_cube = flatten(cubewise)
        flat_cube_with_names = map(self.get_plant_name, flat_cube)
        yield from grouper(flat_cube_with_names, 4)
