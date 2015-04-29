class School
  def initialize
    @school = Hash.new { |hash,key| hash[key] = Array.new }
  end
  
  def add(name, grade)
    @school[grade] << name
    @school[grade].sort!
    return nil
  end
  
  def grade(g)
    @school[g]
  end

  def to_hash
    @school.sort.to_h
  end
end
