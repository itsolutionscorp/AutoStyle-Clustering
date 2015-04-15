class School
  attr_reader :grades

  def initialize
    @grades = {}
  end

  def to_hash
    grades.sort_by { |k, _v| k }.to_h
  end

  def grade(grade)
    grades[grade] || []
  end

  def add(student, grade)
    grades[grade] ||= []
    grades[grade] << student

    grades[grade].sort!
  end
end
