class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    @db[grade] = [] if @db[grade].nil?
    @db[grade] << name
  end

  def grade(grade)
    grade = @db[grade]
    return [] if grade.nil? || grade.empty?
    grade
  end

  def sort
    keys = @db.keys.sort
    keys.inject({}) {|sorted, grade| sorted.merge(grade => @db[grade].sort)}
  end
end
