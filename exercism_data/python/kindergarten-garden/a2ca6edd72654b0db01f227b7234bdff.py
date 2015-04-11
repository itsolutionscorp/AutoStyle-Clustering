plantdict = {
                'V': "Violets",
                'R': "Radishes",
                'C': "Clover",
                'G': "Grass"
                }
        
class Garden(object):
        plants_for = {}
        
        def __init__(self, setup, students=None):
                ## initialize
                self.alltheplants = setup;
                self.first_row = "";
                self.second_row = "";
                self.students = students;

                #check the rows of plants provided
                self.set_rows_of_plants(self.alltheplants);
                #create the student objects
                self.set_students(self.students);
                #assign plants to students
                self.plants2students();
                
        def set_rows_of_plants(self, alltheplants):
                self.first_row = self.alltheplants.split('\n')[0];
                ## if second row exists
                if self.alltheplants.find('\n') != -1:
                        self.second_row = self.alltheplants.split('\n')[1];

        def set_students(self, studentlist):
                if studentlist is None:
                        self.students = [Student('Alice'), Student('Bob'), Student('Charlie'), Student('David'),
                                         Student('Eve'), Student('Fred'), Student('Ginny'), Student('Harriet'),
                                         Student('Ileana'), Student('Joseph'), Student('Kincaid'), Student('Larry')];
                else:
                        self.students = [Student(name) for name in sorted(studentlist)];

        def plants2students(self):
                self.assign_plants(self.first_row);

                if self.alltheplants.find('\n') != -1:
                        self.assign_plants(self.second_row);
                        
        def assign_plants(self, row):
                i = 0;
                for aStudent in self.students:
                        aStudent.assign_plant(row[i]);
                        i+=1;
                        aStudent.assign_plant(row[i]);
                        i+=1;
                        if i >= len(row):
                                break;
                                
        def plants(self, studentname):
                for aStudent in self.students:
                        if aStudent.get_student_name() == studentname:
                                return aStudent.get_plants();


class Student(object):
        
        def __init__(self, name):
                self.Name = name;
                self.plants = ""

        def assign_plant(self, plant):
                self.plants = self.plants + " " + plantdict[plant];

        def get_student_name(self):
                return self.Name;
                                     
        def get_plants(self):
                return self.plants.split();
