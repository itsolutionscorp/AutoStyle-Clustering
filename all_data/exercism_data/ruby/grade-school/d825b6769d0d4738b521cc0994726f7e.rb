class School
  def db
    @db ||= {}
  end

  def add(student, number)
    db[number] = grade(number) + [student]
  end

  def grade(number)
    db.fetch(number, [])
  end

  def sort
    Hash[db.sort.map { |grade, students| [grade, students.sort] }]
  end

end
