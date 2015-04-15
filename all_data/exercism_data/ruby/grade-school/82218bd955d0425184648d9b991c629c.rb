require "forwardable"

class School
  extend Forwardable
  def_delegators :@db, :add, :sort

  def initialize
    @db = StudentDatabase.new
  end

  def grade(grade)
    @db[grade]
  end

  def db
    @db.to_hash
  end
end

class StudentDatabase
  def initialize
    @store = Hash.new
  end

  def to_hash
    @store
  end

  def [](grade)
    @store[grade] || []
  end

  def add(student, grade)
    @store[grade] ||= []
    @store[grade]  << student
  end

  def sort
    sorted_store = {}
    @store.sort.each do |grade, students|
      sorted_store[grade] = students.sort
    end

    sorted_store
  end
end
