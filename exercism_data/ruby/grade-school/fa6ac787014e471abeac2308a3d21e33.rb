class School
  def initialize
    @school = Hash.new { |db, grade| db[grade] = [] }
  end

  def add(student, grade)
    @school[grade] << student
  end

  def grade(level)
    @school[level].sort
  end

  def to_hash
    sorted = @school.map { |grade, students| [ grade, students.sort ] }.sort
    Hash[sorted]
  end
end
