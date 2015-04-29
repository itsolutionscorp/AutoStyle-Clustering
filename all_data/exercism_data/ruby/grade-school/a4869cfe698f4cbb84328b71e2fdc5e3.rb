class School
  attr_reader :db

  def initialize
    @db = Hash.new { [] }
  end

  def add(student, grade)
    db[grade] = db[grade] << student
  end

  def grade(grade)
    db[grade]
  end

  def sort
    Hash[sorted_grades]
  end

  private

  def sorted_grades
    db.map { |grade, students|
      [grade, students.sort]
    }.sort
  end

end
