class School
  def initialize
    @db = {}
  end

  def db
    @db
  end

  def add(name, grade)
    db[grade] ||= []
    @db[grade] << name
  end

  def grade(grade)
    @db[grade] || []
  end

  def sort
    Hash[@db.sort.map { |a| [a[0], a[1].sort] }]
  end
end
