class Garden:

    plant_species = {'C':'Clover','G':'Grass','R':'Radishes','V':'Violets'}
    students = ['Alice','Bob', 'Charlie', 'David','Eve', 'Fred', 'Ginny',
                'Harriet','Ileana', 'Joseph', 'Kincaid', 'Larry']

    # Initialize the garden
    # sequence = garden representation in the format:
    # {plant1_stud1}{plant2_stud1}...\n{plant3_stud1}{plant4_stud1}
    # where each plant is represented by the first letter of its name
    # and '\n' is being used to separate both gardens
    #
    # students = list of students (overrides default list)
    def __init__(self, sequence, students=[]):
        # Validate the sequence string
        if isinstance(sequence, basestring):
            self.sequence = sequence

        # If there is another (valid) list of students, use that
        if isinstance(students, list) and len(students) > 0:
            self.students = sorted(students)

    # Return a list containing the set of plants for a particular student
    def plants(self,student):

        # Get the index of the student in the list (assuming no duplicates)
        child_index = self.students.index(student)

        # Check if the sequence has the length that is required to store this student's plants
        if ((child_index + 1)* 4) + 1 <= len(self.sequence):

            # Get the starting index for this child
            base_index = 2 * child_index

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
