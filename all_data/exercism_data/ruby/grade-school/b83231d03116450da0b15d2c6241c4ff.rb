class DB < Hash
  def keys
    super.sort
  end
end

class School
  attr_reader :db

  def initialize
    @db = DB.new
  end

  def add(name,grade)
    (@db[grade.to_i] ||= []).push(name.to_s.capitalize).uniq!
  end

  def grade(grade)
    @db[grade.to_i] || []
  end

  def sort()
    @db.each { |key, value| value.sort! }
  end
end
