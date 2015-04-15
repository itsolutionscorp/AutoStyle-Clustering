class School
  attr_accessor :db

  def initialize name
    @name = name
    @db = Hash.new { |hash, key| hash[key] = []}
  end

  def add name, grade
    db[grade] << name
  end

  def grade grade
    db[grade] if db[grade]
  end

  def sort
    db.keys.sort.each_with_object({}) { |grade, hash|
      hash[grade] = grade(grade).sort
    }
  end

end
