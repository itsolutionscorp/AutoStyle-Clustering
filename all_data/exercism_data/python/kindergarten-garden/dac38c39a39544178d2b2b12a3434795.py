class Garden:

    plant_species = {'C':'Clover','G':'Grass','R':'Radishes','V':'Violets'}
    students = ['Alice','Bob', 'Charlie', 'David','Eve', 'Fred', 'Ginny',
                'Harriet','Ileana', 'Joseph', 'Kincaid', 'Larry']

    def __init__(self, sequence, students=[]):
        if isinstance(sequence, basestring):
            self.sequence = sequence

        if isinstance(students, list) and len(students) > 0:
            self.students = sorted(students)

    def plants(self,student):
        # Get the index of the student in the list (assuming no duplicates)
        student_index = self.students.index(student)

        # Check if the sequence has the length that is required to store this student's plants
        if ((student_index + 1)* 4) + 1 <= len(self.sequence):

            # Get the starting index for this child
            base_index = 2 * student_index

            # Add the first 2 plants
            result = [self.plant_species[self.sequence[base_index]],
                      self.plant_species[self.sequence[base_index + 1]]]

            # Switch to the other garden and move to this student's second set of plants
            offset = base_index
            while self.sequence[offset] != '\n':
                offset += 1
            offset += base_index + 1

            # Add the second set of plants
            result.append(self.plant_species[self.sequence[offset]])
            result.append(self.plant_species[self.sequence[offset + 1]])

            return result
        else:
            # This child does not have any plants here
            return None
