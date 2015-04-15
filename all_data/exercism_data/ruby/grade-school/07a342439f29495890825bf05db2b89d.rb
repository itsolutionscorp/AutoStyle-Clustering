class School

  def initialize
    @school = Hash.new { [] }
  end

  def to_hash
    Hash[@school.sort]
  end

  def add(name, grade)
    (@school[grade] += [name]).sort!
  end

  def grade(grade)
    @school[grade]
  end
end
