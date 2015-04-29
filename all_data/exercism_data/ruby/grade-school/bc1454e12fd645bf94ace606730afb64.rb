class School
  attr_accessor :db

  def initialize
    @db = Hash.new {|h,k| h[k] = []}
  end

  def add(name, grade)
    db[grade] << name
  end

  def grade(grade)
    db[grade]
  end

  def sort
    Hash[db.sort].each{ |_,k| k.sort!}
  end

end
