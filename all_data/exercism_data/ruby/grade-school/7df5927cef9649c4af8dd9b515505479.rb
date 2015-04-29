class School
  def initialize name
    @name = name
    @students_db = {}    
  end
  
  def db
    @students_db
  end

  def grade grade
    @students_db[grade] || []
  end

  def sort
    @students_db.sort.each_with_object({}) { |(grade, students), sorted| sorted[grade] = students.sort}
  end

  def add student, grade
    (@students_db[grade] ||= []) << student
  end
end
