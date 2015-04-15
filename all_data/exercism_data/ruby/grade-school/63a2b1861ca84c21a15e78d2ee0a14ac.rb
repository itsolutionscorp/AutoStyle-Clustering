class School
  attr_reader :db

  def initialize
    @db = Hash.new([])
  end

  def add(student, grade)
    db[grade] += Array(student)
  end

  def grade(grade)
    db[grade]
  end

  def sort
    Hash[sorted_students.sort]
  end

  private

  def sorted_students
    db.dup.map { |grade, students| [ grade, students.sort ] }
  end
end
