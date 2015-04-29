class School
  attr_reader :db

  def initialize
    @db = Hash.new { Array.new }
  end

  def add(name, grade)
    db[grade] = db[grade] + [name]
  end

  def grade(grade)
    db[grade]
  end

  def sort
    Hash[
      db.sort_by(&:first)
        .map { |grade, names| [grade, names.sort] }
    ]
  end
end
