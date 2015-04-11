class School
  def db
    students.to_hash
  end

  def add(student_name, grade_number)
    students.add student_name, grade_number
  end

  def grade(grade_number)
    get_grade db, grade_number
  end

  def sort
    # to avoid multiple cloning of the database get db once and pass around
    d = db
    Hash[grade_numbers(d).map { |grade_number| sort_grade d, grade_number }]
  end

  private

  def students
    @students ||= Students.new
  end

  def sort_grade(database, grade_number)
    [grade_number, get_grade(database, grade_number).sort]
  end

  def grade_numbers(database)
    database.keys.sort
  end

  def get_grade(database, grade_number)
    database[grade_number] || []
  end
end

class Students
  def add(student_name, grade_number)
    (students[grade_number] ||= []) << student_name
  end

  def to_hash
    # clone/dup is shallow copy but need deeper clone of child arrays aswell
    Hash[students.keys.map { |grade_number| clone_grade(grade_number) }]
  end

  private

  def students
    @students ||= {}
  end

  def clone_grade(grade_number)
    [grade_number, grade(grade_number).clone]
  end

  def grade(grade_number)
    students[grade_number] || []
  end
end
