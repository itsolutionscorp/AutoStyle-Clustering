class School
  def initialize
    @grades = {}
  end

  def add name, grade
    @grades[grade] = [] if @grades[grade] == nil
    @grades[grade].push name
    @grades[grade].sort!
  end

  def grade n
    @grades[n] == nil ? [] : @grades[n]
  end

  def to_hash
    @grades.sort.to_h
  end
end
