class School

  def initialize
    @students = []
  end

  def add(name, grade) 
    @students << Student.new(name,grade)
  end
  
  def to_hash
    hash = Hash.new
    grades.each do |grade|
      hash[grade] = grade(grade)
    end
    hash
  end

  def grade(grade)
    @students.select{|s| s.grade == grade}.map{|s| s.name}.sort
  end

  private
  def grades
    @students.map{|s| s.grade}.uniq.sort
  end

end

class Student
  attr_reader :name, :grade

  def initialize(name,grade)
    @name = name
    @grade = grade
  end 

end
