class School

  def initialize
    @db = Hash.new {|hash, key| hash[key] = []}
  end

  def add(student, grade)
    @db[grade] << student
  end

  def grade(number)
    @db[number]
  end

  def sort
    Hash[@db.sort].each {|k, v| v.sort! }
  end

  private

  attr_accessor :db

end
