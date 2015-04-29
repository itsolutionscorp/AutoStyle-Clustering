require 'set'

class School
  attr_accessor :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    db[grade] ||= []
    db[grade] << name
  end

  def grade(number)
    db.fetch(number, []).dup
  end

  def sort
    db.sort.each_with_object({}) do |entries,hash|
      key, values = entries
      hash[key] = values.sort
    end
  end
end
