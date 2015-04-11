class School
  attr_accessor :db

  def initialize
    @db = Hash.new([])
  end

  def add(name, grade)
    db[grade] += [name]
  end

  def grade(number)
    db[number].sort
  end

  def to_hash
    sort_students
    Hash[db.sort]
  end

  private

  def sort_students
    db.each { |grade, students| db[grade] = students.sort }
  end
end
