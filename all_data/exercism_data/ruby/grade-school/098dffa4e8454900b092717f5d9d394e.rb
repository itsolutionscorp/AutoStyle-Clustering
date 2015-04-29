class School
  
  attr_accessor :db
  
  def initialize
    @db = {}
  end  
  
  def add(name, grade)
    @db.include?(grade) ? @db[grade] << name : @db[grade] = [name] 
  end  
  
  def grade(grade)
    @db.include?(grade) ? @db[grade] : []
  end 
  
  def sort
    @db.keys.sort.reduce({}) { |hash,item| hash.merge( item => @db[item].sort ) }
  end  
   
  
end  
