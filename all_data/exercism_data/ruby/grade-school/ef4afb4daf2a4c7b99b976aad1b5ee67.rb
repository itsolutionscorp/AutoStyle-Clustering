class School

  def initialize
    @db ||= {}
  end

  def db
    @db
  end

  def add(name, grade)
    db[grade] ||= []
    db[grade] << name
  end

  def grade(grade)
    db[grade] || []
  end

  def sort
    db.keys.each do |k|
      db[k].sort!
    end
    Hash[db.sort]
  end

end
