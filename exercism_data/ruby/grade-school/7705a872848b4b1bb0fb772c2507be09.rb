class School
  attr_reader :db

  def initialize(name)
    @db = Hash.new { |h, k| h[k] = [] }
  end

  def add(name, grade)
    @db[grade] << name
  end

  def grade(number)
    @db[number]
  end

  def sort
    @db.keys.sort.each_with_object({}) { |k, h| h[k] = @db[k].sort }
  end
end
