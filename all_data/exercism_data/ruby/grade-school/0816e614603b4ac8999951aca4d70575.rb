class School
  attr_reader :db

  def initialize
    @db = Hash.new(Array.new)
  end

  def add(student, grade)
    @db[grade] += [student]
  end

  def grade(year)
    @db[year]
  end

  def sort
    Hash[@db.each_with_object({}) {|(grade, students), db| db[grade] = students.sort}.sort]
  end
end
