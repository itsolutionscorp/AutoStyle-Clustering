class School

  attr_reader :db

  def initialize
    @db = {}
  end

  def add(student, grade)
    db[grade] ||= []
    db[grade] << student
  end

  def grade(grade)
    db[grade] || []
  end

  def sort
    # Hash[db.sort] # This version is shorter but doesn't sort the value array
    nh = {}
    db.keys.sort.each{ |k| nh[k] = db[k].sort }
    return nh
  end
end
