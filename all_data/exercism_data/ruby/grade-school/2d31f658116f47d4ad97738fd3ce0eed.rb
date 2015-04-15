class School
  def initialize
    @grades = Hash.new { |hash, grade| hash[grade] = [] }
  end

  def add(name, number)
    grade(number).push(name).sort!
  end

  def grade(number)
    @grades[number]
  end

  def to_hash
    @grades.sort.to_h
  end
end
