class Garden

  DEFAULT_STUDENTS = ["Alice", "Bob", "Charlie", "David",
  "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]

  def initialize(diagram, students=DEFAULT_STUDENTS)
    @diagram = diagram
    @students = students
    create_student_methods
  end

  def create_student_methods
    @students.sort.map(&:downcase).each_with_index do
      |student, index|
      define_singleton_method student do
        plant_names(index*2..(index*2)+1)
      end
    end
  end

  def plant_names(positions)
    plant_names_by_row(1, positions) + plant_names_by_row(2, positions)
  end

  def plant_names_by_row(row, positions)
    row_codes = @diagram.split("\n")[row-1].split("")[positions]
    CodeConverter.new(row_codes).convert_codes
  end
end

class CodeConverter

  def initialize(codes)
    @codes = codes
  end

  def convert_codes
    @codes.map {|code| code_conversion[code]}
  end

  def code_conversion
    { "R" => :radishes,
      "C" => :clover,
      "G" => :grass,
      "V" => :violets }
  end
end
