class School
  def initialize
    @grades = Hash.new([])
  end

  def add(name, grade)
    @grades[grade] += [name]
  end

  def grade(grade)
    @grades[grade].sort
  end

  def to_hash
    @grades.keys.sort.map do |key|
      [key, grade(key)]
    end.to_h
  end
end
