DEFAULT_STUDENTS = ["Alice", "Bob", "Charlie", "David",
                    "Eve", "Fred", "Ginny", "Harriet",
                    "Ileana", "Joseph", "Kincaid", "Larry"]

PLANTS = {"G": "Grass", "C": "Clover", "R": "Radishes", "V": "Violets"}

class Garden(object):
    PATCH_WIDTH = 2

    def __init__(self, garden, students=None):
        if not students:
            students = DEFAULT_STUDENTS
        students = sorted(students) # this is dumb. we really should preserve ordering.
        self._garden = {name:patch for name,patch
                        in zip(students, self._parse_garden_patches(garden))}

    def _parse_garden_patches(self, garden):
        patches = []
        for row in garden.splitlines():
            # init new patches
            num_patches = len(row) / self.PATCH_WIDTH
            while num_patches > len(patches):
                patches.append([])
            # populate current row of each patch
            for row_i, plant in enumerate(row):
                patch_i = row_i / self.PATCH_WIDTH
                patches[patch_i].append(PLANTS[plant])
        return patches

    def plants(self, student):
        return self._garden.get(student, [])
