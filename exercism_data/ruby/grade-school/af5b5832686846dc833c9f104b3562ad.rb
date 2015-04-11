require 'set'

class School
  def initialize
    @db = Hash.new{|h,k| h[k] = Set.new}
  end

  def add(student, year)
    @db[year].add(student)
  end

  def db
    Hash[@db.map{|k,v| [k, v.to_a]}]
  end

  def grade(year)
    @db[year].to_a
  end

  def sort
    Hash[@db.sort.map{|k,v| [k, v.to_a.sort]}]
  end
end
