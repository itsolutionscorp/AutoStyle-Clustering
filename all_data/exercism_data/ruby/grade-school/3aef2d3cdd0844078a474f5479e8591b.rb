class School
  attr_reader :db

  def initialize
    @db = Hash.new { |hash, grade| hash[grade] = [] }
  end

  def add(name, grade)
    db[grade] << name
  end

  def grade(level)
    db[level]
  end

  def sort
    db.sort.each_with_object(Hash.new([])) do |item,hash|
      hash[item[0]] = item[1].sort
    end
  end

end
