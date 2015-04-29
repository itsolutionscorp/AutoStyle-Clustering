class School
  attr_reader :db

  def initialize
    @db = Hash.new { |h,k| h[k] = [] }
  end

  def add(name, grade)
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
