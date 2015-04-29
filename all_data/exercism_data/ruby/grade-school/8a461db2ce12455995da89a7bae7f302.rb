require "set"

class School
  attr_reader :db, :name

  def initialize(name)
    @db   = Hash.new{ Array.new }
    @name = name
  end

  def grade(number)
    db[number]
  end

  def add(name, grade)
    @db[grade] = (@db[grade] << name)
  end

  def sort
    @db.each do |grade, collection|
      @db[grade] = collection.sort
    end
    db
  end
end
