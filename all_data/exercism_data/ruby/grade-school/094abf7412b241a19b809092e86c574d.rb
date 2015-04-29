class School
  def initialize
    @db = Hash.new { |db, grade| db[grade] = [] }
  end

  def db
    @db.each_with_object({}) { |(grade, student), db| db[grade] = student.dup }
  end

  def add student, grade
    @db[grade] << student
    nil
  end

  def grade grade
    @db[grade].dup
  end

  def sort
    Hash[@db.sort.map { |grade, students| [grade, students.sort] }]
  end
end
