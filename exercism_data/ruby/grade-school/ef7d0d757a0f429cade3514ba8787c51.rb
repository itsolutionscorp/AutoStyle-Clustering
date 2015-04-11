class School

  attr_reader :db
  def initialize
    @db ||= Hash.new { |students, grade| students[grade] = [] }
  end

  def add(student, grade)
    db[grade] << student
  end

  def grade(grade_level)
    db[grade_level]
  end

  def sort
    sort_array = db.sort
    Hash[sort_array.map { |grade,students| [grade, students.sort] } ]
  end
end
