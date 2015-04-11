class School
  
  attr_accessor :db
  
  def initialize
    @db = Hash.new {|hash, key| hash[key] = []}
  end
  
  def add(student, grade)
    @db[grade] = @db[grade].push(student)
  end
  
  def grade(number)
    @db[number]
  end
  
  def sort
    Hash[@db.sort].each {|k, v| v.sort! }
  end
  
end
