class School
  attr_accessor :students

  def initialize
    @students = Hash.new([])
  end

  def to_hash
    Hash[students.sort]
  end

  def add(name, grade)
    students[grade] += [name]
    students[grade].sort!
  end

  def grade(grade)
    students[grade]
  end
end
