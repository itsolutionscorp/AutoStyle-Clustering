class School
  attr_accessor :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    @db[grade.to_i] ||= []
    @db[grade.to_i] << name
  end

  def grade(q)
    @db.key?(q) ? @db[q] : []
  end

  def sort
    sort = {}
    @db.each_pair do |grade, names|
      sort[grade] = names.sort
    end
    Hash[sort.sort]
  end

end
