class School
  def db
    @db ||= {}
  end

  def add(student, grade)
    initalize_grade(grade)
    db[grade].push(student)
  end

  def grade(grade)
    initalize_grade(grade)
    db[grade]
  end

  def sort
    sorted_values = db.each do |key, val| 
      db[key] = val.sort
    end
    Hash[sorted_values.sort]
  end

  private

  def initalize_grade(grade)
    db[grade] ||= []
  end

end
