class School
  def initialize name
    @name = name
    @students_db = Hash.new(Array.new)    
  end
  
  def db
    @students_db
  end

  def grade grade
    db[grade] 
  end

  def sort
    db.sort.each_with_object({}) { |(grade, students), sorted| sorted[grade] = students.sort}
  end

  def add student, grade
    db[grade] += [student]
  end
end
