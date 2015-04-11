# Note: 
#   This is a quick implementation to get the tests to pass, 
#   I'll tidy it up for the next submission

class School

  attr_accessor :students
  def initialize
    @students = Hash.new { |hsh, key| hsh[key] = [] }
  end

  def to_hash
    students.each { |key, value| students[key] = grade(key) }.sort_by { |key,value| key }.to_h
  end

  def add(name, grade)
    students[grade] << name
  end

  def grade(grade)
    students[grade].sort
  end

end
