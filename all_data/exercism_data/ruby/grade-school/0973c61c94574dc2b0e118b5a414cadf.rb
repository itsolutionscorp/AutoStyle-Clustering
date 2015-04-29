require 'set'

class School
  def initialize
    @db = Hash.new{ |h,k| h[k] = Set.new }
  end

  def add(student, year)
    @db[year].add(student)
    nil
  end

  def to_hash
    @db.keys.sort.each_with_object({}) { |y,h| h[y] = grade(y) }
  end

  def grade(year)
    @db[year].sort
  end
end
