class School
  attr_reader :db

  def initialize
    @db = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(name, grade)
    @db[grade] ||= Array.new
    @db[grade] << name
  end

  def grade(level)
    @db[level]
  end

  def sort
    sorted_db = Hash.new
    sorted_keys = @db.keys.sort
    sorted_keys.each do |k|
      sorted_db[k] = @db[k].sort
    end

    sorted_db
  end
end
