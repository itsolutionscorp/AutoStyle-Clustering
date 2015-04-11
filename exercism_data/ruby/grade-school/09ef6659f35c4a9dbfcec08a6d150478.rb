class School
  def initialize
    @students = Hash.new([])
  end

  def add(student, grade)
    @students[grade] = @students[grade] + [student]
    @students[grade].sort!
  end

  def grade(grade)
    @students[grade]
  end

  def to_hash
    Hash[@students.keys.sort.collect { |k| [k, @students[k]] }]
  end
end
