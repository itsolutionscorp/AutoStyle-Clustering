class School
  attr_reader :school

  def initialize
    @school = {}
  end

  def add(student, grade)
    current_students = Array(school[grade])
    school[grade] = (current_students << student).sort
  end

  def grade(grade)
    Array(@school[grade])
  end

  def to_hash
    Hash[school.sort]
  end

end
