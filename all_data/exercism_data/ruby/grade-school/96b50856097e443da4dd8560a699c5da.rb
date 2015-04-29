class School

  attr_accessor :db

  def initialize
    @db = Hash.new { |h,k| h[k] = [] }
  end

  def add(student, grade)
    @db[grade].push(student)
  end

  def grade g
    @db[g]
  end

  def sort
    Hash[@db.each { |k,v|  v.sort!}.sort]
  end

end
