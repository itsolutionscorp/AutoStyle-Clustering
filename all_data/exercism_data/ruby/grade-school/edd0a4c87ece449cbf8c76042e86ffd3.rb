class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    @db[grade] = [] unless @db[grade]
    @db[grade] << name
  end

  def grade(grade)
    @db[grade] || []
  end

  def sort
    result = {}
    @db.keys.sort.each do |key|
      result[key] = @db[key].sort
    end
    result
  end
end
