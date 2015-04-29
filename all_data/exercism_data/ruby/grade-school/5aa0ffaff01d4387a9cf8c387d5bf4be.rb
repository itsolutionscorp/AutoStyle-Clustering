class School
  def initialize
    @db = empty_school
  end

  def add(name, grade)
    db[grade] << name
  end

  def grade(level)
    db[level]
  end

  def sort
    db.values.each(&:sort!)
    db.sort.to_h
  end

  def db
    @db
  end

  private
  def empty_school
    Hash.new { |hash, grade| hash[grade] = empty_classroom }
  end

  def empty_classroom
    Array.new
  end
end
