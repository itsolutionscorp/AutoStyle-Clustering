class Garden
  attr_reader :students, :diagram

  STUDENTS = %w(Alice Bob Charlie David Eve Fred Ginny
                Harriet Ileana Joseph Kincaid Larry)

  PLANTS = {
    "G" => :grass,
    "C" => :clover,
    "R" => :radishes,
    "V" => :violets
  }

  def initialize(diagram, students = STUDENTS)
    @diagram = diagram
    @students = students.sort


    @students.each_with_index do |student, i|
      metaclass = class << self; self; end
      metaclass.send(:define_method, student.downcase) do
        plants_for i
      end
    end
  end

  private
  def plants_for(student_index)
    @diagram.split("\n").each_with_object([]) do |row, plants|
      row.slice(student_index * 2, 2).chars.each do |code|
        plants << PLANTS[code]
      end
    end
  end
  
end
