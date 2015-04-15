class School

  attr_accessor :db

  def initialize
    @db = {}
  end

  def add student, grade
    @db[grade] = @db[grade].to_a << student
  end

  def grade grade
    @db[grade] || []
  end

  def sort
    @db.keys.sort.reduce({}) do |new_db, key|
      new_db[key] = @db[key].sort
      new_db
    end
  end
end
