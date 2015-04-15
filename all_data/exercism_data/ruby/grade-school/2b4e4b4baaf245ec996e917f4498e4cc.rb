class School
  
  attr_accessor :db
  
  def initialize
    @db = {}
  end  
  
  def add(name, a_grade)
    @db[a_grade] ||= []
    @db[a_grade] += [name]
  end  
  
  def grade(a_grade)
    @db.fetch(a_grade, []) 
  end 
  
  def sort
    sorted_db = Hash[@db.sort]
    sorted_db.each { |k,v| sorted_db[k] = v.sort }
  end  
   
  
end  
