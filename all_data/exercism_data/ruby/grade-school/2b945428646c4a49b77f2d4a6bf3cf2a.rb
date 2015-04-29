require "forwardable"

class School
  extend Forwardable
  def_delegators :@db, :add, :sort
  def_delegator  :@db, :[], :grade
  def_delegator  :@db, :to_hash, :db

  def initialize
    @db = StudentDatabase.new
  end
end

class StudentDatabase
  def initialize
    @store = {}
  end

  def to_hash
    @store
  end

  def [](key)
    @store[key] || []
  end

  def add(value, key)
    @store[key] ||= []
    @store[key]  << value
  end

  def sort
    sorted_tuples = @store.sort_by do |key, values|
      values.sort!

      key
    end

    Hash[sorted_tuples]
  end
end
