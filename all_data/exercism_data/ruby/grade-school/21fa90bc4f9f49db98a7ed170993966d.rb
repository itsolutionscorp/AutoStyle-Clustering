class School
  def initialize
    @school_hash = Hash.new { |roster, grade| roster[grade] = [] }
  end
  
  def add(student, grade)
    @school_hash[grade] << student
  end
  
  def to_hash
    sorted = @school_hash.map { |grade, student| [grade, student.sort] }.sort
    Hash[sorted]
  end
  
  def grade(grade)
    @school_hash[grade].sort    
  end
end
