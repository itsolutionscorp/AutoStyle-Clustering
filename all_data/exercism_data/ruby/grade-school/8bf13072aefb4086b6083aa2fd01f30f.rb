class School
  def initialize
    @grades = Hash.new do |grades, grade_level|
      grades[grade_level] = Grade.new(grade_level)
    end
  end

  def add(student, grade_level)
    grades[grade_level].add_student(student)
  end

  def grade(grade_level)
    grades[grade_level].students
  end

  def db
    to_hash(grades.values) do |grade|
      grade.to_hash
    end
  end

  def sort
    to_hash(grades.values.sort) do |grade|
      grade.sort.to_hash
    end
  end

  private

  attr_reader :grades

  def to_hash(array_of_grades, &block)
    array_of_grades.each_with_object(Hash.new) do |grade, result|
      result.merge!(block.call(grade)) unless grade.empty?
    end
  end

end

class Grade
  include Comparable

  attr_reader :level, :students

  def initialize(level, students=nil)
    @level = level
    @students = students || []
  end

  def add_student(student)
    students << student
  end

  def empty?
    students.empty?
  end

  def sort
    Grade.new(level, students.sort)
  end

  def sort!
    @students = students.sort
    self
  end

  def <=>(other_grade)
    level <=> other_grade.level
  end

  def to_hash
    hash = {}
    hash[level] = students.clone
    hash
  end
end
