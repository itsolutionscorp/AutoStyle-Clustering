class School
  def initialize
    @school_students = Hash.new([])
  end
  def add(name, grade)
    (@school_students[grade] += [name]).sort!
  end
  def grade(grade)
    @school_students[grade].sort
  end
  def to_hash
    @school_students.to_a.sort.to_h
  end
end
