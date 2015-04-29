class School
  def db
    @db ||= Hash.new { |db, grade| db[grade] = [] }
  end

  def add(name, grade)
    db[grade] << name
  end

  def grade(grade)
    db[grade]
  end

  def sort
    Hash[db.sort].each_value(&:sort!)
  end
end
