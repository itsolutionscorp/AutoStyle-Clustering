class School
  def initialize
    @grades = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(name, grade)
    (@grades[grade] << name).sort!
  end

  def grade(grade)
    @grades[grade]
  end

  def to_hash
    @grades.sort.to_h
  end
end
