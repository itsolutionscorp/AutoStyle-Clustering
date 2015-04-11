class School
  def initialize
    @students = []
  end

  def to_hash
    Hash.new { |h, k| h[k] = [] }.tap do |h|
      @students.each { |s| h[s.grade] << s.name }
    end
  end

  def add(name, grade)
    @students << Student.new(name, grade)
    @students.sort!
    self
  end

  def grade(grade)
    @students.select { |s| s.grade == grade }.map(&:name)
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
