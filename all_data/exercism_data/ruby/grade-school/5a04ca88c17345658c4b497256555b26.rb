class School

  def db
    @db ||= {}
  end

  def add(name, num)
    grade(num) << name
  end

  def grade(num)
    db[num] || db[num] = []
  end

  def sort
    db.each_value { |n| n.sort! }
    Hash[db.sort]
  end

end
