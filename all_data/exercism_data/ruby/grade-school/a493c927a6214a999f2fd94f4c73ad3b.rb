class School
  attr_reader :grades

  def initialize
    # A grade has an empty list of students by default
    @grades = Hash.new { |hash, grade| hash[grade] = [] }
  end

  def to_hash
    grades.sort_by { |k, _v| k }.to_h
  end

  def grade(grade)
    grades[grade]
  end

  def add(student, grade)
    grade(grade) << student

    grade(grade).sort!
  end
end
