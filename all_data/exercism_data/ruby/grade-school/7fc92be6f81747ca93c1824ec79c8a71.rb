class School

  attr_reader :students

  def initialize
    @students = Hash.new { |h, k| h[k] = [] }
  end

  def add(name, grade)
    students[grade] << name
  end

  def to_hash
    students.select { |k,v| students[k] = v.sort() }
    Hash[students.sort]
  end

  def grade(level)
    students[level].sort
  end

end
