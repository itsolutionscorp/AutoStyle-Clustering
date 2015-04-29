class School

  def initialize
    @db = {}
  end 

  def db
    @db.dup.freeze
  end
   
  def grade(n)
    @db[n] || []
  end
  
  def add(student, grade)
    @db[grade] = grade(grade) << student
  end
  
  def sort
    Hash[@db.sort.map { |grade, students| [grade, students.sort] }]
  end
  
end
