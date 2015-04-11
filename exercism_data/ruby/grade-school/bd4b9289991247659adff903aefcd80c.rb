class School
  def initialize
    @db = Hash.new { |hash, key| hash[key] = Array.new }
  end

  def db
    @db.dup
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
