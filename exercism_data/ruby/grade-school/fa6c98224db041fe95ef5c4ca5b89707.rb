class School

  def initialize
    @grades = Hash.new
  end

  def to_hash
    @grades.sort.to_h
  end

  def add(name, grade)
    @grades[grade] ||= []
    @grades[grade] << name
    @grades[grade].sort!
  end

  def grade(grade)
    @grades[grade] || []
  end

end
