class School
  def initialize
    @db = Database.new
  end

  def add(student, grade)
    @db.add(student, grade)
  end

  def grade(level)
    @db.grade(level)
  end

  def sort
    @db.sort
  end

  def db
    @db.db
  end
end

class Database
  attr_reader :db

  def initialize
    @db = Hash.new{ |db,key| db[key] = [] }
  end

  def add(student, grade)
    @db[grade] = add_student(grade, student)
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    @db.sort.each_with_object({}) do |(grade, student) , school|
      school[grade] = student.sort
    end
  end

  private

  def add_student(grade, student)
    @db[grade].push(student)
  end
end
