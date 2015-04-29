class School
  attr_reader :db

  def initialize
    @db = Hash.new { |db, grade| db[grade] = [] }
  end

  def add(name, grade)
    @db[grade] << name
  end

  def grade(level)
    @db[level]
  end

  def sort
    sorted_grades = @db.sort.map { |grade, students| [grade, students.sort!] }
    Hash[sorted_grades]
  end
end
