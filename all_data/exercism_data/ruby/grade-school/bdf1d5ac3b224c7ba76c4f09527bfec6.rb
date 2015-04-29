class School
  def db
    @db ||= Hash.new([])
  end

  def add(student, grade)
    db[grade] += Array(student)
  end

  def grade(grade)
    sort[grade] || []
  end

  def sort
    sorted_students = db.each do |grade, student| 
      db[grade] = student.sort
    end
    Hash[sorted_students.sort]
  end

  def to_hash
    sort
  end
  
end
