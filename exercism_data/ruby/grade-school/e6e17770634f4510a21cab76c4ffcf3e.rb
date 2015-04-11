class School
  def initialize
    @db = BetterHash.new do |db,new_grade|
      db[new_grade] = []
    end
  end

  def db
    @db.transform_values do |students|
      students.map &:dup
    end
  end

  def add student, grade
    @db[grade] << student.dup
  end

  def grade grade
    db.fetch grade, []
  end

  def sort
    db.transform_values do |students|
      students.sort
    end.sort
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
