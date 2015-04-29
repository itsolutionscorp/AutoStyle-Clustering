class School

  attr_accessor :db

  def initialize
    @db = Hash.new([])
  end

  def add(student, grade)
    if @db.has_key? grade then
      @db[grade].push(student)
    else
      @db[grade] = [student]
    end
  end

  def grade g
    @db[g]
  end

  def sort
    Hash[@db.each { |k,v|  v.sort!}.sort]
  end

end
