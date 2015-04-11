class School
  def initialize
    @db = Hash.new { |hash, key| hash[key] = [] }
  end

  def db
    @db
  end

  def add(name, grade)
    @db[grade] << name
  end

  def grade(level)
    @db[level]
  end

  def sort
    Hash[
      db.sort.reduce([]) { |memo, a| memo << [a[0], a[1].sort] }
    ]
  end
end
