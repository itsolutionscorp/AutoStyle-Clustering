class School

  attr_reader :students

  def initialize
    @students = Hash.new { |student, grade| student[grade] = [] }
  end

  def db
    students
  end

  def add(name, grade)
    students[grade] << name
  end

  def grade(level)
    students[level]
  end

  def sort
    sorted_students = students.sort.map { |grade, students| [grade, students.sort] }
    Hash[sorted_students]
  end
end
