class School
  def initialize
    @db = Hash.new { |db, grade| db[grade] = [] }
  end

  def db
    @db.each_with_object({}) { |(grade, students), db|
      db[grade] = students.dup
    }
  end

  def add student, grade
    @db[grade] << student
    nil
  end

  def grade grade
    @db[grade].dup
  end

  def sort
    @db.sort
       .each_with_object({}) { |(grade, students), db|
         db[grade] = students.sort
       }
  end
end
