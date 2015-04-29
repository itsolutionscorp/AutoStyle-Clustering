class School
  def db
    #deep cloning
    Marshal.load(Marshal.dump(internal_db))
  end

  def add(name, grade)
    internal_db[grade] ||= []
    internal_db[grade] << name
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

private
  def internal_db
    @db ||= {}
  end
end
