class School
  def initialize
    @grades = {}
  end

  def add name, grade
    @grades[grade] ||= []
    @grades[grade] << name
    @grades[grade].sort!
  end

  def grade n
    @grades[n] || []
  end

  def to_hash
    @grades.sort.to_h
  end
end
