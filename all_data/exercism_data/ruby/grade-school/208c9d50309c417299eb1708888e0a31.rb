class School
  attr_reader :grades
  def initialize
    @grades = {}
  end

  def add(student, grade)
    grades.merge!({grade => []}) unless grades.has_key?(grade)
    grades[grade] << student
    grades[grade].sort!
  end

  def grade(number)
    grades[number] || []
  end

  def to_hash
    Hash[grades.sort]
  end
end
