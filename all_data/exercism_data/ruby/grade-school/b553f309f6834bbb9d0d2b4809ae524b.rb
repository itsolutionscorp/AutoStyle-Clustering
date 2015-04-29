class School
  attr_reader :db

  def initialize
    @db = Hash.new{ |db, grade| db[grade] = [] }
  end

  def add(name, grade_name)
    grade(grade_name) << name
  end

  def grade grade_name
    @db[grade_name]
  end

  def sort
    sorted = @db.map{ |grade, students| [grade, students.sort] }.sort
    Hash[sorted]
  end
end
