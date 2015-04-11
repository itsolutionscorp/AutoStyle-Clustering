class School
  attr_accessor :db
  
  def initialize
    @db = Hash.new
  end
  
  def add(name, grade)
    @db[grade] ? @db[grade] << name : @db[grade] = [name]
  end
  
  def sort
    sorted_db = {}
    @db.sort.each { |grade, names| sorted_db[grade] = names.sort }
    @db = sorted_db
  end
  
  def grade(grade)
    @db[grade] ||= []
  end
  
end
