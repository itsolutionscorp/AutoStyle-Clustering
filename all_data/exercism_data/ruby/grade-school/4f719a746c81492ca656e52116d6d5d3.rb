class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    db[grade] ||= []
    db[grade] << name
  end

  def grade(group)
    db[group] ||= []
  end

  def sort
    Hash[db.each_key{|k| db[k].sort!}.sort]
  end


end
