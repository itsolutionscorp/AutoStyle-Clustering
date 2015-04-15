class Student 
  include Comparable

  attr_reader :grade, :name
  def initialize(name, grade) 
    @name = name
    @grade = grade
    freeze
  end

  def <=>(other)
    grade <=> other.grade 
  end
end

class Students

  def initialize
    @students = []
    freeze
  end

  def add(name, grade)
    @students << Student.new(name, grade)
    @students.sort!
  end

  def names_for(grade)
    @students.inject([]) do |names, student|
      names << student.name if student.grade == grade
      names
    end.sort
  end

  def grouped_by_grades
    @students.inject({}) do |grouped_students, student|
      grouped_students[student.grade] ||= []
      grouped_students[student.grade] << student.name
      grouped_students 
    end
  end

end

class School

  def initialize
    @students = Students.new
  end


  def add(name, grade)
    @students.add(name, grade)
  end

  def grade(grade)
    @students.names_for(grade)
  end

  def to_hash
    @students.grouped_by_grades
  end

end
