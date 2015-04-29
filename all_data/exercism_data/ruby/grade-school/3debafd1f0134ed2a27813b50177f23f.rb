class School
  def initialize
    @grades = Hash.new {|this, grade| this[grade] = []}
  end

  def to_hash
    Hash[@grades.sort]
  end

  def add (name, grade)
    @grades[grade].push(name).sort!
  end

  def grade(num)
    @grades[num].sort
  end

end
