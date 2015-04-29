class School
  def initialize(name)
    @name = name
    @db = Hash.new { |db, grade_level| db[grade_level] = [] }
  end

  attr_reader :db

  def add(student, grade_level)
    grade(grade_level) << student
  end

  def grade(level)
    db[level]
  end

  def sort
    sorted = db.sort.map { |grade_level, students| [grade_level, students.sort] }
    Hash[sorted]
  end
end
