class School
  def initialize
    @grades = Hash.new { |grades, n| grades[n] = [] }
  end

  def to_hash
    Hash[@grades.keys.sort.map{ |n| [n, grade(n)] }]
  end

  def add(student, grade)
    @grades[grade] << (student)
  end

  def grade(grade)
    @grades[grade].sort
  end
end
