class School
  def db
    @db ||= Hash.new { |k,v| k[v] = [] }
  end

  def add(student, grade)
    db[grade] << student
  end

  def grade(grade)
    db[grade]
  end

  def sort
    Hash[sort_students]
  end

  private

  def sort_students
    db.each_pair { |k,v| db[k] = v.sort }.sort
  end
end
