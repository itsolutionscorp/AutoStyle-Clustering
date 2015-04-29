class School
  def add(name, grade_number)
    students[grade_number] = (students_by_grade(grade_number) << name).sort
  end
  def students
    @students ||= {}
  end
  def grade(number)
    students_by_grade(number)
  end
  def students_by_grade(number)
    students[number] || []
  end
  def to_hash
    Hash[students.sort]
  end
end
