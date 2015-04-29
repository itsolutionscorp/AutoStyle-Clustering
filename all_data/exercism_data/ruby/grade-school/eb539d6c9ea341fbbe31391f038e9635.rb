class School
 
  attr_reader :db
  
  def initialize
    @db = Hash.new { [] }
  end

  def add student, grade
    db[grade] <<= student
  end
  
  def grade level
    db.fetch(level) { [] }
  end
  
  def sort
    Hash[db.each { |_,students| students.sort! }.sort]
  end

end
