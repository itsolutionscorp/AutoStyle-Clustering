class School
  def initialize
    @class = {}
  end

  def to_hash
    @class
  end

  def add(name, grade)
    new_students_in_grade = students_in_grade(grade) << name
    @class[grade] = new_students_in_grade.sort
  end

  def students_in_grade(grade)
    @class.fetch(grade) { [] }
  end
end
