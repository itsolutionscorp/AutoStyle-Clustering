class School

  attr_accessor :db

  def initialize
    @db = Hash.new([])
  end

  def add(student, grade)
    @db[grade] = [] if @db[grade].nil?
    @db[grade] = [student]
  end

  def grade g
    @db[g]
  end

  def sort
    Hash[@db.each { |k,v|  v.sort!}.sort]
  end

end
