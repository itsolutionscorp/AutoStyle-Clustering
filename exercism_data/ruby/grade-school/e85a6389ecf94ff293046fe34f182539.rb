class School
  attr_reader :db

  def initialize
    @db = Database.new
  end

  def db
    @db.to_hash
  end

  def add(student, grade_num)
    @db[grade_num] << student
  end

  def grade(grade_num)
    @db[grade_num]
  end

  def sort
    @db.sorted_keys.each_with_object({}) do |grade_num, sorted|
      sorted[grade_num] = grade(grade_num).sort
    end
  end
end

class Database
  def initialize
    @db = Hash.new { |hash, key| hash[key] = [] }
  end

  def sorted_keys
    @db.keys.sort
  end

  def [](key)
    @db[key]
  end

  def to_hash
    @db
  end
end
