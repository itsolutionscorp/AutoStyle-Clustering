class Garden
  SEED_MAP = {
    'C' => :clover,
    'G' => :grass,
    'R' => :radishes,
    'V' => :violets
  }

  DEFAULT_STUDENTS = %w(Alice Bob Charlie David Eve Fred Ginny Harriet Ileana
                        Joseph Kincaid Larry)

  def initialize(diagram, students = DEFAULT_STUDENTS)
    @diagram = diagram
    @students = students.sort
  end

  def method_missing(method_name)
    student = student(method_name)
    if in_class?(student)
      plants(student)
    else
      super
    end
  end

  def respond_to_missing?(method_name, include_all)
    in_class?(student(method_name)) || super
  end

  private

  attr_reader :diagram, :students

  def plants(student)
    diagram_index = students.index(student) * 2
    seeds = diagram.each_line.map { |row| row[diagram_index, 2] }.join
    seeds.each_char.map { |seed| SEED_MAP[seed] }
  end

  def student(method_name)
    method_name.to_s.capitalize
  end

  def in_class?(student)
    students.include?(student)
  end
end
