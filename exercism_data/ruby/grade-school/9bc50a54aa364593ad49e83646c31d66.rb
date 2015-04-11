class School
  attr_reader :db
  def initialize(thing)
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
    db.each.with_object({}) do |(k, v), hash|
      hash[k] = v.sort
    end
  end
end
