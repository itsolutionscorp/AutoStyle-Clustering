class School

  attr_reader :db
  def initialize
    @db = Hash.new{ |db, level| db[level] = [] }
  end

  def add(name, level)
    db[level] << name
  end

  def grade(level)
    db[level]
  end

  def sort
    sorted = db.map { |level, students| [level, students.sort] }.sort
    Hash[sorted]
  end
end
