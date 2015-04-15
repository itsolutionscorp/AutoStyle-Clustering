class School
  def initialize
    @students = Hash.new{ |a,b| a[b] = Array.new }
  end

  def add(name, grade)
    @students[grade] << name
  end

  def sort
    @students.each_value(&:sort!)
    Hash[@students.sort]
  end

  def grade(grade)
    @students[grade]
  end

  def db
    @students
  end

end
