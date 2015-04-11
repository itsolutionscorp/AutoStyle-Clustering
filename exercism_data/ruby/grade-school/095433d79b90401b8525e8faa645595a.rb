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
    # Hash[db.sort] # This version is shorter but doesn't sort the value array
    nh = {}
    db.keys.sort.each{ |k| nh[k] = db[k].sort }
    return nh
  end
end
