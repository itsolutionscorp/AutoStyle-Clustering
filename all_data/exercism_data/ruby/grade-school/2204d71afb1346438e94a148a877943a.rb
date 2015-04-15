class School
  def initialize
    @students = Hash.new { [] }
  end

  def add(name, grade_number)
    @students[grade_number] += [name]
  end

  def db
    @students
  end

  def grade(grade_number)
    @students.fetch grade_number, []
  end

  def sort
    Hash[@students.map { |k, v| [k, v.sort] }.sort_by &:first]
  end
end
