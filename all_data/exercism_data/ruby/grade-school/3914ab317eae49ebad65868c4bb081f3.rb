class School
  attr_accessor :db

  def initialize
     @db = Hash.new{ |students, grade|  students[grade] = [] }
  end

  def add(name,grade)
    @db[grade] << name
  end

  def grade(level)
    @db[level]
  end

  def sort
    sorted = db.map { |grade, students| [ grade, students.sort ]}.sort
    Hash[sorted]
  end
end


#school = School.new
#school.add('jalil', 3)
#puts school.grade(3)
