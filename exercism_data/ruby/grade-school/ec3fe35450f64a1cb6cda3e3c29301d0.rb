class School
  def initialize(name)
    @name = name
    @db = Hash.new { |h, k| h[k] = [] }
  end

  attr_reader :db

  def add(student, grade_number)
    grade(grade_number) << student
  end

  def grade(number)
    db[number]
  end

  def sort
    Hash[db.map { |k, v| [k, v.sort] }.sort]
  end
end
