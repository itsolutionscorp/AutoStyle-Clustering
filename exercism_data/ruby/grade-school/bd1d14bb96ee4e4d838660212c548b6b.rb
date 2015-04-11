class School

  def initialize
    @students = {}
  end

  def to_hash
    @students.values.each { |student| student.sort! }
    @students.sort_by { |grade, students| grade }.to_h
  end

  def add(name, grade)
    @students[grade] ? @students[grade] << name : @students[grade] = [name]
  end

  def grade(grade)
    to_hash[grade] ? to_hash[grade] : []
  end
end
