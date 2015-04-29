class School

  attr_accessor :db

  def initialize
    @db_private = Hash.new {|hash, key| hash[key] = []}
    @db =  copy(@db_private)
  end

  def copy(inputhash)
    inputhash.each_with_object(Hash.new([])) {|(k,v), db| db[k] = v.dup }
  end

  def add(student, grade)
    @db_private[grade] = @db_private[grade].push(student)
    @db =  copy(@db_private)
  end

  def grade(number)
    @db = copy(@db_private)
    @db[number]
  end

  def sort
    @db =  copy(@db_private)
    Hash[@db.sort].each {|k, v| v.sort! }
  end

end
