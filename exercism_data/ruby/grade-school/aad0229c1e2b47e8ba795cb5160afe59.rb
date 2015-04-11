class School
  def db
    @db ||= {}
  end

  def add(name, year)
    db[year] ||= []
    db[year] << name
    db
  end

  def grade(year)
    db[year] || []
  end

  def sort
    Hash[db.group_by {|k,v| [k, v.sort]}.keys.sort]
  end
end
