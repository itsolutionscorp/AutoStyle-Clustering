class School
  def initialize
    @school = Hash.new { |hash,key| hash[key] = Array.new }
  end
  
  def add(name, grade)
    @school[grade].push(name).sort!
  end
  
  def grade(g)
    @school[g]
  end

  def to_hash
    @school.sort.to_h
  end
end
