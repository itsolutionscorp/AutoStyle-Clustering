class School
  def initialize
    @students = Hash.new { |hash, key| hash[key] = [] }
  end

  def to_hash
    Hash[@students.sort]
  end

  def add(name, grade)
    @students[grade] = (@students[grade] << name).sort
  end

  def grade(number)
    @students[number]
  end
end
