class School
  attr_reader :db

  def initialize
    @db = Hash.new { |h, k| h[k] = [] }
  end

  def add student_name, grade
    db[grade] << student_name
  end

  def grade grade
    db[grade]
  end

  def sort
    sorted_db = db.sort
    Hash[sorted_db.map { |k, v|
      [k, v.sort]
    }]
  end

end
