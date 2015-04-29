class School
  def initialize
    @grades = [] 
  end
  def to_hash
    result = Hash.new { [] }
    @grades.sort.each {|grade, name| result[grade] <<=name}
    result
  end
  def add(name, grade)
    @grades << [grade, name]
  end
  def grade(num)
    to_hash[num]
  end
end
