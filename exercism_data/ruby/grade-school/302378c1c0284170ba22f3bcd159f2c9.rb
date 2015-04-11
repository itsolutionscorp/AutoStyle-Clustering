class School

  attr_accessor :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    db[grade] ||= []
    db[grade] << name
  end

  def grade(grade)
    db[grade] || []
  end

  def sort
    sorted_collection = db.sort.map { |grade, students| [grade, students.sort] }
    Hash[sorted_collection]
  end

end
