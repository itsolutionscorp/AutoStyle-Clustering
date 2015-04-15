class School

  def initialize
    @classes = Hash.new { |h, k| h[k] = [] }
  end

  def add(name, grade)
    @classes[grade] << name
    @classes[grade].sort!
  end

  def grade(num)
    @classes[num]
  end

  def to_hash
    @classes.sort.to_h
  end

end
