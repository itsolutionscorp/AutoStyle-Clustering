class School

  attr_accessor :db

  def initialize
    @db_private = Hash.new {|hash, key| hash[key] = []}
    @db =  copy(@db_private)
  end

  def copy(inputhash)
    h = Hash.new
    inputhash.each do |k,v|
      h[k] = v.dup
    end
    h
  end

  def add(student, grade)
    @db_private[grade] = @db_private[grade].push(student)
    @db =  copy(@db_private)
  end

  def grade(number)
    @db_private[number]
  end

  def sort
    @db =  copy(@db_private)
    Hash[@db.sort].each {|k, v| v.sort! }
  end

end
