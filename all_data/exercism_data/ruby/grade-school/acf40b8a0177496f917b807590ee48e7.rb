class School

  def db
    @db ||= {}
  end

  def add(name, num)
    grade(num) << name
  end

  def grade(num)
    db[num] ||= []
  end

  def sort
    Hash[db.each_value{|v|v.sort!}.sort]
  end

end
