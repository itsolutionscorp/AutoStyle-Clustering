class School
  def db
    @db ||= Hash.new { |k,v| k[v] = [] }
  end

  def add(student, grade)
    db[grade] << student
  end

  def grade(grade)
    db[grade]
  end

  def sort
    Hash[grade_sort]
  end

  private

  def grade_sort
    students_sort.sort
  end

  def students_sort
    db.each { |_, students| students.sort! }
  end
end
