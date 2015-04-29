class School
  attr_accessor :db

  def initialize()
    @db = Hash.new { |h,k| h[k] = [] }
  end

  def add(name, grade)
    @db[grade] << name
  end

  def grade(num)
    @db[num]
  end

  def sort
    Hash[@db.sort.map { |grade, names| [grade, names.sort] }]
  end
end
