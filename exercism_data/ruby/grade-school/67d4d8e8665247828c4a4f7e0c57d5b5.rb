# school.rb
# gradeschool example

class School
  attr_accessor :db
  
  def initialize()
    @db = Hash.new { |h,k| h[k]=[] }
  end
  
  def add(student, grade)
    @db[grade].push(student)
  end
  
  def grade(g)
    @db[g]
  end
  
  def sort
    Hash[@db.sort].each_value { |v| v.sort! }
  end
end
