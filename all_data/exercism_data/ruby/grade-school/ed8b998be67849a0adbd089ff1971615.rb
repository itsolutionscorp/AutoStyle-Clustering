class School
  def initialize
    @db = Database.new
  end

  def add(name, grade)
    @db.add(name, grade)
  end

  def grade(grade)
    @db.grade(grade)
  end

  def sort
    @db.sort
  end

  def db
    @db.db
  end

  private

  def add_student(grade, name)
    @db[grade].push(name)
  end
end

class Database
  attr_reader :db

  def initialize
    @db = Hash.new{ |db,key| db[key] = [] }
  end

  def add(name, grade)
    @db[grade] = add_student(grade, name)
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    @db.sort.each_with_object({}) do |(grade, name) , school|
      school[grade] = name.sort
    end
  end

  private

  def add_student(grade, name)
    @db[grade].push(name)
  end

end
