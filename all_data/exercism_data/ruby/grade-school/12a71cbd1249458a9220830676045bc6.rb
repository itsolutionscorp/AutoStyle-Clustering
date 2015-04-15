class Student < Struct.new(:name, :grade); end

class School
  def initialize
    @students_by_grade = {}
  end

  def to_hash
    Hash[@students_by_grade.sort] 
  end

  def grade(grade)
    to_hash[grade] || []
  end

  def add name, grade 
    initialize_grade(grade) if first_grade_inserted?(grade) 
    @students_by_grade[grade].push(name).sort!
  end

  private

  def first_grade_inserted?(grade)
    @students_by_grade[grade].nil?
  end

  def initialize_grade(grade)
    @students_by_grade[grade] = []
  end

end
