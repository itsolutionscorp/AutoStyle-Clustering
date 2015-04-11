class School
  def initialize
    @school = Hash.new([])
  end

  def add(student, grade)
    @school[grade] += [student]
  end

  def to_hash
    @school
  end

  def grade(grade)
    @school[grade].sort
  end

end
