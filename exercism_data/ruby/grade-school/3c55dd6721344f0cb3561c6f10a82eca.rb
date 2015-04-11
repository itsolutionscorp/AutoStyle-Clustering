class School
  attr_reader :name, :db

  def initialize(name)
    @name = name
    @db = Hash.new { |students, grade| students[grade] = [] }
  end

  def add(student, grade)
    db[grade] << student
  end

  def grade(grade)
    db[grade]
  end

  def sort
    # or db.deep_dup.each_value(&:sort!)
    sorted = db.map { |grade, students| [ grade, students.sort ] }
    Hash[sorted]
  end
end
