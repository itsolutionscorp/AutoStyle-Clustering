class School
  attr_accessor :db

  def initialize
    @db = Hash.new {|hash, key| hash[key] = [] }
  end

  def add(name, grade)
    db[grade] << name
  end

  def grade(grade)
    db[grade]
  end

  def sort
    key_sort = Hash[db.sort]
    Hash[key_sort.map { |key,value| [key, value.sort]}]
  end
end
