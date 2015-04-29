class School

  def initialize
    @students = Hash.new()
  end

  def to_hash
    sorted_grades = Hash.new()
    @students.sort.each { |grade, students| sorted_grades[grade] = students }
    sorted_grades

  end

  def add(name, grade)
    @students[grade] = (@students[grade] ? (@students[grade] << name).sort : [name] )
  end

  def grade(number)
    @students.fetch(number, [])
  end

end
