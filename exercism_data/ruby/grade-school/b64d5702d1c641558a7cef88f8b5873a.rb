class School
  def initialize
    @students = []
  end

  def to_hash
    @students.sort.group_by(&:grade).map do |grade, students|
      [grade, students.map(&:name)]
    end.to_h
  end

  def add(name, grade)
    @students << Student.new(name, grade)
  end

  def grade(grade)
    @students.sort.select { |s| s.grade == grade }.map(&:name)
  end
end

class Student
  include Comparable

  attr_reader :name, :grade

  def initialize(name, grade)
    @name = String(name)
    @grade = Integer(grade)
  end

  def <=>(other)
    (grade <=> other.grade).nonzero? || name <=> other.name
  end
end
