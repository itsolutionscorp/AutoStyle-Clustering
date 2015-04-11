class School
  def initialize(name)
    @name = name
    @students = {}
  end
  
  def add(student, grade)
    if @students.has_key?(grade)
      @students[grade] << student
    else
      @students[grade] = [student]
    end
  end
  
  def grade(number)
    @students.has_key?(number) ? @students[number] : []
  end
  
  def sort
    Hash[@students.sort_by {|k,v| -k}.reverse].each {|k,v| v.sort!}
  end
  
  def db
    @students
  end  
end
