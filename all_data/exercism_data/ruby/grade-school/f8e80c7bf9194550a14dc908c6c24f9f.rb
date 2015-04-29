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
    @db.keys.sort.inject({}) do |sorted, key|
      sorted[key] = @db[key].sort
      sorted
    end
  end
end
