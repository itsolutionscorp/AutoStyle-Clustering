class School
  attr_reader :db

  def initialize()
    @db = Hash.new { |hash, key| hash[key] = [] if key.is_a? Fixnum }
  end

  def add(student, grade)
    @db[grade] << student if student.is_a? String
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    Hash[@db.sort.map { |grade, students| [grade, students.sort] }]
  end
end
