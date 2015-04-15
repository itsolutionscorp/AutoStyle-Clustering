class School
  def initialize
    @grades = {}
  end

  def add(student, grade)
    @grades[grade] ||= []
    @grades[grade] << student
    @grades[grade].sort!
  end

  def grade(grade)
    @grades[grade] ||= []
    @grades[grade].sort!
  end

  def to_hash
    Hash[@grades.sort]
  end
end
