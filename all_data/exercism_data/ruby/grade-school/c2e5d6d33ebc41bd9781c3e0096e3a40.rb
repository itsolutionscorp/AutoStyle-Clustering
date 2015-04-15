require 'pry'

class School
  attr_reader :db

  # in the event of a unknown hash it intiailizes a new hash
  def initialize
    @db = Hash.new {|hash,grade| hash[grade] = [] }
  end

  def add name, grade
    @db[grade].push name
  end

  def grade year
    @db[year]
  end

  def sort
    Hash[@db.sort]
  end
end
