class School
  attr_reader :db

  def initialize
    @db = Hash.new([])
  end

  def add(name, grade)
    @db[grade] += [name]
  end

  def grade(key)
    @db[key]
  end

  def sort
    @db.keys.sort.inject({}) do |result, key|
      result[key] = @db[key].sort
      result
    end
  end
end
