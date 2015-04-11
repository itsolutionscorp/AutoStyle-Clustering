class Garden

  PLANT_KEY = {
    radishes: "R",
    clover: "C",
    grass: "G",
    violets: "V"
  }

  DEFAULT_STUDENTS = %w[alice bob charlie david eve fred ginny harriet ileana joseph kincaid larry]

  def initialize(diagram, students = DEFAULT_STUDENTS)
    @diagram = diagram
    @students = students.map(&:downcase).sort
    create_methods
  end

  private

  def get_garden_at(student_index)
    student_positions = [student_index * 2, student_index * 2 + 1]
    plants = []
    student_positions.each do |column_number|
      plants << plant_at(column_number)
    end
    plants.transpose.flatten
  end

  def plant_at(column_number)
    plants = []
    @diagram.split("\n").each do |line|
      plants << PLANT_KEY.invert.fetch(line[column_number])
    end
    plants
  end


  def create_methods
    @students.each_with_index do |student, index|
      define_singleton_method student do
        get_garden_at(index)
      end
    end
  end



end
