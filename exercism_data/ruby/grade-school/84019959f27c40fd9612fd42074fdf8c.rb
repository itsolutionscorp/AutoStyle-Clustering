class School
  def initialize
    @db = Hash.new([])
  end

  def db
    @db.map { |grade, students| [grade, students.dup] }.to_h
  end

  def add(student, grade)
    @db[grade] += [student]
  end

  def grade(grade)
    @db[grade].dup
  end

  def sort
    @db.sort.map { |grade, students| [grade, students.sort] }.to_h
  end
end
