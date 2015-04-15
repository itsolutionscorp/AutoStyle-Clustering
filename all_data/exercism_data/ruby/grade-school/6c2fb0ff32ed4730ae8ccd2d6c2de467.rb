class School
  def initialize(name)
    @name = name
    @students = Hash.new {|h, k| h[k] = []}
  end
  
  def add(student, grade)
    @students[grade] << student
  end
  
  def grade(number)
    @students[number]
  end
  
  def sort
    Hash[@students.sort_by {|k,v| -k}.reverse].each {|k,v| v.sort!}
  end
  
  def db
    @students
  end  
end
