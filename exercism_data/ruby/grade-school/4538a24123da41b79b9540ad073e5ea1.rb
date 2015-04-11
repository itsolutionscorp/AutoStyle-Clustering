class School
  def db
    students
  end

  def add(name, grade)
    (students[grade] ||= []) << name
  end

  def grade(grade_number)
    students[grade_number] || []
  end

  def sort
    Hash[sorted_grade_numbers.map { |grade_number| sort_grade(grade_number) }]
  end

  private

  def students
    @students ||= {}
  end

  def sort_grade(grade_number)
    [grade_number, grade(grade_number).sort]
  end

  def sorted_grade_numbers
    students.keys.sort
  end
end
