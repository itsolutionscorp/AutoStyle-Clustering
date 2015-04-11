class School

  def initialize
    @school = Hash.new([])
  end

  def add(student, grade)
    (school[grade] += [student]).sort!
  end

  def grade(grade)
    school[grade]
  end

  def to_hash
    Hash[school.sort]
  end

  private

  attr_reader :school

end
