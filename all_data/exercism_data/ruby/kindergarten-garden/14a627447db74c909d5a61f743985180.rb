class Garden

  PLANTS = {
    radishes:  "R",
    clover: "C",
    grass: "G",
    violets: "V"
  }

  DEFAULT_STUDENTS = ["alice", "bob", "charlie", "david", "eve", "fred", "ginny", "harriet", "ileana", "joseph", "kincaid", "larry"]

  CUPS_PER_STUDENT = 2

  def initialize(diagram, students=DEFAULT_STUDENTS)
    @diagram  = diagram.split(/\n/)
    @students = students.map(&:downcase).sort
    create_student_methods(@diagram, @students)
  end

  private
  def create_student_methods(diagram, students)
    (class << self; self; end).class_eval do
      students.each_with_index do |student, i|
        define_method student do 
          diagram.map do |row|
            row.chars[(i*CUPS_PER_STUDENT..i*CUPS_PER_STUDENT+1)].map do |plant|
              PLANTS.key(plant)
            end
          end.flatten
        end
      end
    end
  end

end
