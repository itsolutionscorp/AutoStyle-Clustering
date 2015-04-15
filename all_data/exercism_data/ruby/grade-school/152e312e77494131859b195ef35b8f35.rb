class School

  attr_reader :db
  def initialize
    @db = Hash.new { [] }
  end

  def add(student, grade)
    db[grade] <<= student
  end

  def grade(level)
    db[level]
  end

  def sort
    Hash[db.sort.map { |grade, students| [ grade, students.sort ] } ]
  end
end
