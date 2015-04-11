class Garden
  def initialize(diagram, students = DEFAULT_STUDENTS)
    @diagram, @students = diagram, students.sort
    define_student_accessors
  end

private

  DEFAULT_STUDENTS = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred",
                      "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]

  PLANTS = { "G" => :grass, "C" => :clover, "R" => :radishes, "V" => :violets }

  STUDENT_PLANTS_PER_ROW = 2

  def define_student_accessors
    @students.each do |student|
      define_singleton_method(student.downcase) { translate letters_for(student) }
    end
  end

  def letters_for(student)
    @diagram.split.map { |line| line[index_of(student),STUDENT_PLANTS_PER_ROW] }.join
  end

  def index_of(student)
    @students.find_index(student) * STUDENT_PLANTS_PER_ROW
  end

  def translate(letters)
    letters.chars.map { |letter| PLANTS[letter] }
  end
end
