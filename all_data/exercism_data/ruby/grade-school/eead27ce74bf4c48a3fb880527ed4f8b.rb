class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    grade(grade) << name
  end

  def grade grade
    @db[grade] ||= []
  end

  def sort
    sorted = {}
    @db.keys.sort.each do |key|
      sorted[key] = @db[key].sort
    end
    sorted
  end
end
