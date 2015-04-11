class School
 
 attr_reader :db
  
  def initialize
    @db = Hash.new { |hash,key| hash[key] = [] }
  end

  def add student, grade
    db[grade] << student
  end
  
  def grade level
    db[level]
  end
  
  def sort
    Hash[db.each { |k,v| v.sort! }.sort]
  end
  
end
