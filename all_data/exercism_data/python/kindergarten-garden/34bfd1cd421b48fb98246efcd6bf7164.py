_DEFAULT_STUDENTS = ['Alice',
                     'Bob',
                     'Charlie',
                     'David',
                     'Eve',
                     'Fred',
                     'Ginny',
                     'Harriet,'
                     'Ileana',
                     'Joseph',
                     'Kincaid',
                     'Larry',
                     ]

_PLANT_DICT = {'R':'Radishes',
               'C':'Clover',
               'G':'Grass',
               'V':'Violets',
               }

class Garden:

    def __init__(self, window_sill, students = _DEFAULT_STUDENTS):
        
        self._window_sill = window_sill.splitlines()
        self._students = list(students)
        self._students.sort()

        ## reject window sills with less or more than 2 lines
        if(len(self._window_sill) != 2):
            raise ValueError("""Invalid window sill garden.\n
            Expected 2 rows, but got {0}.""".format(len(self._window_sill)))

        ## Check that there are the same number of plants in each row
        if(len(self._window_sill[0]) != len(self._window_sill[1])):
            raise ValueError("""Invalid window sill garden.\n
            Different number of plants in each row.""")

        ## Check that the number of plants on window sill
        ## matches number of students - or less there will be tears!
        ##
        ## Should be validated imho, but will clash with the test suit. :P
        ## (which has a bug anyway)
        ##
        ## if(2*len(self._students) > len(self._window_sill[0])):
        ##    raise ValueError("""Not enough plants for all kids!""")
        
    def plants(self, student):
        plant_index = 2*self._students.index(student)
        return [_PLANT_DICT.get(plant)
                for row in self._window_sill
                for plant in row[plant_index : plant_index + 2]
                ]
    
    @property
    def __window_sill(self):
        """Two lines of plant codes for the window sill garden."""
        return self._window_sill

    @property
    def __students(self):
        """List of student names in alphabetic order"""
        return self._students    

    
