class School
  
  attr_accessor :db
  
  def initialize
    @db = {
      
    }
  end  
  
  def add(name, grade)
    if @db.include? grade
      @db[grade] << name 
    else
      @db[grade] = [name] 
    end  
  end  
  
  def grade(grade)
    return @db[grade] if @db.include? grade
    []
  end 
  
  def sort
    @db.keys.sort.reduce({}) { |hash,item| hash.merge( item => @db[item].sort ) }
  end  
   
  
end  
