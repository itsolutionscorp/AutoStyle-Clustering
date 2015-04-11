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
    sort_students
  end

  def sort_students
    db.each { |grade, students| students.sort }
  end
end
