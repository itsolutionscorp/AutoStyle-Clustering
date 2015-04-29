class School

  def initialize
    @enrollment = {}
    @grades = []
    12.times { |x| @grades << [] }
  end

  def add(student, grade)
    @enrollment[grade] = (@grades[grade+1] << student)
    @enrollment[grade].sort!
  end

  def to_hash
    Hash[@enrollment.sort]
  end

  def grade(grade)
    return @enrollment[grade] unless @enrollment[grade].nil?
    []
  end

end
