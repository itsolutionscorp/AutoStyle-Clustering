class School
  def initialize
    @db = Hash.new([])
  end

  def db
    @db
  end

  def add(student, grade)
    @db[grade] += [student]
  end

  def grade(num)
    @db[num]
  end

  def sort
    Hash[(@db.each { |key, value| value.sort! }).sort]
  end
end
