class School
  def initialize
    @school = Hash.new { Array.new }
  end

  def add(name, grade)
    @school[grade] = @school[grade].push(name).sort
  end

  def grade(n)
    @school[n].clone
  end

  def to_hash
    @school.sort.to_h
  end
end
