class School

  attr_reader :db

  def initialize
    @db = {}
  end

  def add(student, grade)
    db.fetch(grade){|g| db[g] = [] } << student
  end

  def grade(grade)
    db.fetch(grade, [])
  end

  def sort
    db.each_value(&:sort!)
    Hash[db.sort]
  end
end
