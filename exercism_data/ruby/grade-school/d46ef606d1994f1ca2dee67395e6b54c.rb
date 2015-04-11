class School
  def initialize
    @grades = {}
  end

  def to_hash
    @grades.sort_by { |grade, _names| grade }.to_h
  end

  def add(name, grade)
    grade(grade).push(name).sort!
  end

  def grade(number)
    @grades[number] || @grades[number] = []
  end
end
