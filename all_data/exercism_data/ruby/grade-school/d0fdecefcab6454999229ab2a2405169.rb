class School
  def initialize
    @db = StudentDatabase.new
  end

  def add(name, grade)
    @db.add(grade, name)
  end

  def db
    @db.to_hash
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    @db.sort
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

  def add(key, value)
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
