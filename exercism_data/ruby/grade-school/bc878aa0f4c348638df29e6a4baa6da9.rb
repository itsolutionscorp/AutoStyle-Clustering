class School
  attr_reader :db

  def initialize
    @db = Hash.new([])
  end

  def add(student_name, grade_level)
    db[grade_level] += [student_name]
  end

  def grade(level)
    db.fetch(level, [])
  end

  def sort
    p sort_students
    Hash[sort_students.sort]
  end

  def sort_students
    db.map { |grade, students| [grade, students.sort] }
  end
end
