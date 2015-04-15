class School
  attr_reader :db
  
  def initialize
    @db = Hash.new([])
  end
  
  def add(name, grade)
    @db[grade].empty? ? @db[grade] = [name] : @db[grade] << name
  end
  
  def grade(grade)
    @db[grade]
  end
  
  def sort
    sorted = {}
    @db.keys.sort.each { |key| sorted[key] = @db[key].sort }
    sorted
  end
end
