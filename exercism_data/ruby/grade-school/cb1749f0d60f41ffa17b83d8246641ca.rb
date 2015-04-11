class School
  def db
    students.to_hash
  end

  def add(student_name, grade_number)
    students.add(student_name, grade_number)
  end

  def grade(grade_number)
    students.by_grade(grade_number)
  end

  def sort
    Hash[students.grade_numbers.map { |grade_number| sort_grade(grade_number) }]
  end

  private

  def students
    @students ||= Students.new
  end

  def sort_grade(grade_number)
    [grade_number, grade(grade_number).sort]
  end
end

class Students
  def add(student_name, grade_number)
    grade(grade_number) << student_name
  end

  def by_grade(grade_number)
    (students[grade_number] || []).clone
  end

  def grade_numbers
    students.keys.sort
  end

  def to_hash
    # clone/dup is shallow copy but need deeper clone of child arrays aswell
    Hash[grade_numbers.map { |grade_number| clone_grade(grade_number) }]
  end

  private

  def students
    @students ||= {}
  end

  def grade(grade_number)
    students[grade_number] ||= []
  end

  def clone_grade(grade_number)
    [grade_number, by_grade(grade_number)]
  end
end
