class School
  attr_accessor :db

  def initialize
    @db = {}
  end

  def add(student, grade)
    db[grade] ? db[grade] << student : db[grade] = [student]
  end

  def grade(year)
    db[year] ||= []
  end

  def sort 
    Hash[school_array.sort]
  end

  def school_array
    db.collect { |grade, names| [grade, names.sort] }
  end

end
