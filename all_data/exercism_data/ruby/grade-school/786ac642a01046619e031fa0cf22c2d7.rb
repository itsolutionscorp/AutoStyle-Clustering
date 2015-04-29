class School

  def initialize
    @db = Hash.new { |h, k| h[k] = [] } 
  end

  def add(name, grade)
    @db[grade] << name
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    Hash[ @db.map { |k,v| [k, v.sort] }.sort ]
  end

  def db
    @db.dup
  end
end
