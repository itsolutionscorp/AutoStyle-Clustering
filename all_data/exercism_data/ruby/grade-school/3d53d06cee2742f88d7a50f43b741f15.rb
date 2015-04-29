class School

  def initialize
    @students = {}
  end

  def to_hash
    Hash[@students.sort_by{|k,v| k}]
  end

  def add(student, a_grade)
    @students.store(a_grade, (grade(a_grade) << student).sort)
  end

  def grade(a_grade)
    @students.fetch(a_grade, [])
  end

end
