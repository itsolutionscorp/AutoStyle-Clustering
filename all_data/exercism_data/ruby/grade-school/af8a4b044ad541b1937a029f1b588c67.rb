class School

  def db
    @db ||= Hash.new { |students, grade| students[grade] = [] }
  end

  def add(name, grade)
    db[grade] << name
  end

  def grade(number)
    db[number]
  end

  def sort
    sorted = db.collect { |grade, students| [ grade, students.sort ] }.sort
    Hash[sorted]
  end
end
