class School
 
  attr_reader :db
  
  def initialize
    @db = Hash.new { [] }
  end

  def add student, grade
    db[grade] <<= student
  end
  
  def grade level
    db.fetch(level) { [] }.sort
  end
  
  def to_hash
    Hash[db.sort.each { |_,students| students.sort! }]
  end

end
