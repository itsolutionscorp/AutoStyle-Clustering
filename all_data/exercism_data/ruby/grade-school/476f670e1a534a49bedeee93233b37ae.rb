class School

  def initialize
    @school = Hash.new { |school, grade| school[grade] = Array.new }
  end

  def add(student, grade)
    @school[grade] << student
  end

  def grade(number)
    @school[number].sort
  end

  def to_hash
    sorted = @school.map { |grade, students| [ grade, students.sort ] }.sort
    sorted.to_h
  end
end
