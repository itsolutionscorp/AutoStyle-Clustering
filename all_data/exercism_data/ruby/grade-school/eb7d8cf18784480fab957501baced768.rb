class School
  def initialize
    @students = Hash.new{[]}
  end

  def add(student, grade)
    @students[grade] += [student]
    @students[grade].sort!
  end

  def grade(grade_num)
    @students[grade_num]
  end

  def to_hash
    @students.to_a.sort.to_h
  end
end
