class Kindergarten:

    demo_students = ( 
        'Alice', 'Bob', 'Charlie', 'David',
        'Eve', 'Fred', 'Ginny', 'Harriet',
        'Ileana', 'Joseph', 'Kincaid', 'Larry'
    )

    def __init__(self, students):
        self._students = sorted(students or Kindergarten.demo_students)

    def index(self, name):
        try:
            return self._students.index(name)
        except ValueError:
            raise ValueError("No child found for name '" + name + "'")

class Plant:

    def __init__(self, abbreviation, name):
        self.abbreviation = abbreviation
        self.name = name

class Plants:

    repository = (
        Plant('G', 'Grass'),
        Plant('C', 'Clover'),
        Plant('R', 'Radishes'),
        Plant('V', 'Violets')
    )

    @staticmethod
    def from_abbreviations(abbreviations):
        return [ 
            Plants.get(abbreviation)
            for abbreviation in abbreviations
        ]

    @staticmethod
    def get(abbreviation):
        for plant in Plants.repository:
            if (plant.abbreviation == abbreviation):
                return plant
        raise ValueError("No plant found for abbreviation '" + abbreviation + "'")

class Patch:

    columns = 2
    rows = 2

    def __init__(self, rows):
        self.rows = rows

    def serialize_plant_names(self):
        return [
          self.rows[r][c].name 
          for r in range(0, Patch.rows)
          for c in range(0, Patch.columns) ]

class GardenLayoutParser:

    def __init__(self, layout):
        self._rows = layout.split("\n")
        self._validate_patches()
        self._create_patches()

    def _validate_patches(self):
        if (len(self._rows) != Patch.rows):
            raise ValueError("Unexpected number of rows in layout " +
                             "(expected " + repr(Patch.rows) + ")")

        row_lengths = [ len(row) for row in self._rows ]
        if (min(row_lengths) != max(row_lengths)):
            raise ValueError("Not all rows in layout are of equal length") 

        self._row_length = row_lengths[0]
        if (self._row_length % Patch.columns != 0):
            raise ValueError("Unexpected number of columns in layout " +
                             "(expected a multiple of " + repr(Patch.columns) + ")")

    def _create_patches(self):
        self.patches = [ ]
        for offset in [ o for o in range(0, self._row_length, Patch.columns) ]:
            patch_rows = [ ]
            for i, row in enumerate(self._rows):
                abbreviations = row[offset:offset + Patch.columns]
                patch_row = Plants.from_abbreviations(abbreviations)
                patch_rows.append(patch_row)
            self.patches.append(Patch(patch_rows))

class Garden:

    def __init__(self, layout, students = None):
        self._kindergarten = Kindergarten(students) 
        self._patches = GardenLayoutParser(layout).patches

    def plants(self, name):
        patch = self.patch(name)
        return patch.serialize_plant_names()

    def patch(self, name):
        idx = self._kindergarten.index(name)
        try:
            return self._patches[idx]
        except IndexError:
            raise ValueError("No patch found for '" + name + "'")
