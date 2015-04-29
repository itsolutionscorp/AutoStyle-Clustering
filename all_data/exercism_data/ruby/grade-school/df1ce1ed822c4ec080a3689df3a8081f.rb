class School
  def db
    @db ||= {}
  end

  def add(name, grade)
    db[grade] ||= []
    db[grade] << name
  end

  def grade(grade)
    db[grade] || []
  end

  def sort
    Hash[db.sort].inject({}) do |acc, entry|
      acc[entry.first] = entry.last.sort
      acc
    end
  end
end
