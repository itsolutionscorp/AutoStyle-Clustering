class School
  attr_reader :db

  def initialize
    @db = Hash.new { |students, grade| students[grade] = [] }
  end

  def add(student, grade)
    db[grade] << student
  end

  def grade(grade)
    db[grade]
  end

  def sort
    Hash[ db.map { |_, students| [_, students.sort] }.sort]
  end
end
