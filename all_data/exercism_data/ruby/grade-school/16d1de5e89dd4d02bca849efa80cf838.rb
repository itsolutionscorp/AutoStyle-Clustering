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
    grades.values.each_with_object(Hash.new) do |grade, result|
      result.merge!(grade.to_hash) unless grade.empty?
    end
  end

  def sort
    grades.values.sort.each_with_object(Hash.new) do |grade, result|
      result.merge!(grade.sort.to_hash) unless grade.empty?
    end
  end

  private

  attr_reader :grades
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
