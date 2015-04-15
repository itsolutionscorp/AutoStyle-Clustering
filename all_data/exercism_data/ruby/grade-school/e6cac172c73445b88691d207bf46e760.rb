class School

  attr_accessor :db

  def initialize
    @db ||= {}
  end

  def add(name, grade)
    db[grade] ||= []
    db[grade] << name
  end

  def grade(n)
    db[n] || []
  end

  def sort
    db.values.map(&:sort!)
    Hash[db.sort]
  end

end
