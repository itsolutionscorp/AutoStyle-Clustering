class School
  attr_reader :db
  
  def initialize
    @db = Hash.new([])
  end
  
  def add(name, grade)
    @db[grade] += [name]
  end
  
  def grade(grade)
    @db[grade]
  end
  
  def sort
    Hash[@db.sort.map { |grade,name| [grade, name.sort] }]
  end
end
