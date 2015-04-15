class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    @db[grade] ||= []
    @db[grade] << name
  end

  def grade(num)
    @db[num] || []
  end

  def sort
    Hash[@db.sort_by { |grade, name| grade }.map{|k,v| [k,v.sort]}]
  end

end
