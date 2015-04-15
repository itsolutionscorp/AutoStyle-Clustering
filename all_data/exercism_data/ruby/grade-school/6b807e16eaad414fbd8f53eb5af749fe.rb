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
    r = {}
    @db.keys.sort.each { |y| r[y] = grade(y) }
    r
  end

  def grade(year)
    @db[year].sort
  end
end
