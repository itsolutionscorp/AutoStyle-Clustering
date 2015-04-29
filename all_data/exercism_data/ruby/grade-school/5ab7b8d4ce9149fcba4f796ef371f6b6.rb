class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    @db[grade] ||= []
    @db[grade].push(name)
  end

  def grade(number)
    @db[number] ||= []
  end

  def sort
    sorted = {}
    @db.sort.each do |a, b|
      sorted[a] = b
      sorted[a] = sorted[a].sort
    end
    sorted
  end

end
