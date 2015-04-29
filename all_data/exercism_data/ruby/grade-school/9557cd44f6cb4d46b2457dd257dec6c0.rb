class School
  attr_reader :db

  def initialize
    @db = BetterHash.new do |db,new_grade|
      db[new_grade] = []
    end
  end

  def add student, grade
    db[grade] << student
  end

  def grade grade
    db[grade]
  end

  def sort
    db.transform_values{|students| students.sort}.sort
  end
end

class BetterHash < Hash
  def transform_values
    self.class[ map{|key,value| [key,yield(value)]} ]
  end

  def sort
    self.class[ super ]
  end
end
