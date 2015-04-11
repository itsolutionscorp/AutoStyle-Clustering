class School
  def initialize
    @grades = {}
  end
  
  def add(student, grade)
    @grades[grade] = [] if @grades[grade].nil?
    
    @grades[grade] << student
    @grades[grade].sort!
  end
  
  def to_hash
    result = {}
    @grades.keys.sort.each {|k| result[k] = @grades[k]}
    return result
  end
  
  def grade(n)
    return @grades[n].nil? ? [] : @grades[n]
  end
end
