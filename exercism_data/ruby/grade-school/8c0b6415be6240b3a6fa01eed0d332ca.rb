class School
  def initialize name
    @name = name
  end

  def add name, student_grade
    db[student_grade] = grade(student_grade).push name
  end

  def grade student_grade
    db.fetch student_grade, []
  end

  def sort
    db.sort.each_with_object({}) do |(student_grade, names), sorted_db|
      sorted_db[student_grade] = names.sort
    end
  end

  def db
    @db ||= {}
  end
end
