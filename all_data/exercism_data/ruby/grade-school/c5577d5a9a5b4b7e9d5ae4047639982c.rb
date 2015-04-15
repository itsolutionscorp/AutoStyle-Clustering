class School
  def initialize
    @db = Hash.new { [] }
  end

  def add(student, grade)
    db[grade] = db[grade] << student
  end

  def db
    @db
  end

  def grade(grade)
    db[grade]
  end

  def sort
    Hash[db.sort].each do |_, students|
      students.sort!
    end
  end
end
