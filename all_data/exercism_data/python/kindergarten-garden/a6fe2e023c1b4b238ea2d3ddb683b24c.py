class Garden():

    def __init__( self, garden,
                  students=("Alice Bob Charlie David "+
                            "Eve Fred Ginny Harriet " +
                            "Ileana Joseph Kincaid Larry").split() ):
        students.sort()
        self.students = students
        self.garden_row_1 = garden.split('\n')[0]
        self.garden_row_2 = garden.split('\n')[1]

    def plants( self, name ):
        student_index = self.students.index( name )
        return [ self.plant_name( self.garden_row_1[ 2*student_index ] ),
                 self.plant_name( self.garden_row_1[ 2*student_index+1 ] ),
                 self.plant_name( self.garden_row_2[ 2*student_index ] ),
                 self.plant_name( self.garden_row_2[ 2*student_index+1 ] )]

    def plant_name( self, let ):
        return { 'V':'Violets',
                 'R':'Radishes',
                 'C':'Clover',
                 'G':'Grass' }[let]
