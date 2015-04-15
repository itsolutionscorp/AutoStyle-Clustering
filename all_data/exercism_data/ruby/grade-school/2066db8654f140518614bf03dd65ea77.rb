require 'pry'
class School
  attr_reader :db

  def initialize
    @db = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(name, grade)
    @db[grade] << name
  end

  def grade(level)
    @db[level]
  end

  def sort
    sorted_grades = @db.sort.to_a.each { |grade| grade[1].sort! }
    Hash[sorted_grades]
  end
end
