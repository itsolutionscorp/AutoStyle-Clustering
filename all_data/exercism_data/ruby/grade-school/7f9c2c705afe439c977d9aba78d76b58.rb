class School
  def db
    # deep copy to also clone arrays of students in grades
    Hash[grade_numbers.map { |grade_number| clone_grade grade_number }]
  end

  def add(student_name, grade_number)
    (students[grade_number] ||= []) << student_name
  end

  def grade(grade_number)
    get_grade(grade_number).clone
  end

  def sort
    Hash[grade_numbers.map { |grade_number| sort_grade grade_number }]
  end

  private

  def students
    @students ||= {}
  end

  def grade_numbers
    students.keys.sort
  end

  def sort_grade(grade_number)
    [grade_number, get_grade(grade_number).sort]
  end

  def clone_grade(grade_number)
    [grade_number, grade(grade_number)]
  end

  def get_grade(grade_number)
    students[grade_number] || []
  end
end
