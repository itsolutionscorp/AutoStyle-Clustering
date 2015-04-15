class School

  attr_accessor :db

  def initialize
    @db_private = Hash.new {|hash, key| hash[key] = []}
    @db =  Hash.new {|hash, key| hash[key] = []}
  end

  def copy(inputhash)
    h = Hash.new
    inputhash.each do |pair|
      h.store(pair[0], pair[1])
    end
    h
  end

  def add(student, grade)
    @db_private[grade] = @db_private[grade].push(student)
    @db[grade] = @db[grade].push(student)
  end

  def grade(number)
    @db_private[number]
  end

  def sort
    Hash[@db_private.sort].each {|k, v| v.sort! }
  end

end
