class School
  def initialize
    @students = Hash.new([])
  end

  def add(name, grade)
    @students[grade] = [] unless @students.has_key?(grade)
    @students[grade] << name
    @students[grade].sort!
    @students = Hash[@students.sort]
  end

  def grade(grade)
    @students[grade]
  end

  def to_hash
    @students
  end
end
