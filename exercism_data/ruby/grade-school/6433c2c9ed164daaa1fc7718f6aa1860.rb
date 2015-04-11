class School
  attr_reader :db
  def initialize(name)
    @db = Hash.new { |h,k| h[k] = [] }
  end

  def add(student, grade)
    @db[grade] << student
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    sorted = @db.sort.map { |grade, students|
      [grade, students.sort]
    }
    Hash[sorted]
  end
end
