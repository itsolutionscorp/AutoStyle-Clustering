class School
  def initialize
    @students = Hash.new { |h, k| h[k] = [] }
  end

  def to_hash
    Hash[students.sort_by { |k,v| k }]
  end

  def add(name, grade)
    students[grade] << name
    students[grade].sort!
  end

  def grade(number)
    students[number]
  end

  private

  attr_reader :students
end
