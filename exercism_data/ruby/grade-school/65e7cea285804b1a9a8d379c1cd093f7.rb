class School
  attr_reader :db

  def initialize
    @db = Hash.new { Array.new }
  end

  def add(name, grade)
    db[grade] = db[grade] << name
  end

  def grade(grade)
    db[grade]
  end

  def sort
    old_db = db
    initialize
    old_db.sort.each do |grade, names|
      names.sort.each { |name| add name, grade }
    end
    db
  end
end
