class School
  def initialize
    @school = Hash.new {|h,k| h[k] = []}
  end
  
  def to_hash
    @school.each_pair {|k, v| v.sort!}
    Hash[@school.sort]
  end
  
  def add(student, grade)
    @school[grade] << student
  end
  
  def grade(num)
    @school[num].sort
  end
end
