class School
  attr_accessor :db

  def initialize
    @db = Hash.new([])
  end

  def add(name, grade)
    if @db[grade].empty?
      @db[grade] = Array.new
    end

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
