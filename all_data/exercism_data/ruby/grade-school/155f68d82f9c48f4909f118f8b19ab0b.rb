class School
  attr_reader :db

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
    sorted = {}
    db.keys.sort.each { |grade| sorted[grade] = @db[grade].sort }
    sorted
  end
end
