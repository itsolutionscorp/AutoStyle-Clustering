class School

  def initialize
    @students = Hash.new{|h,k| h[k] = [] }
  end

  def to_hash
    Hash[@students.sort]

  end

  def add(name, grade)
    @students[grade] = (@students[grade] << name).sort
  end

  def grade(number)
    @students.fetch(number, [])
  end

end
