class School
  def initialize
    @students = Hash.new
  end

  def to_hash
    Hash[@students.sort]
  end

  def add(name, grade)
    @students[grade] ||= []
    @students[grade] << name
    @students[grade].sort!
  end

  def grade(num)
    @students[num] || []
  end
end
