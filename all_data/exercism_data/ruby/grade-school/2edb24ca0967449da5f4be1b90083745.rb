class School

  attr_reader :db

  def initialize
    @db = Hash.new { |h,k| h[k] = [] }
  end

  def add(name, grade)
    db[grade].push(name)
  end

  def grade(class_num)
    db[class_num]
  end

  def sort
    Hash[db.each_key{|k| db[k] = db[k].sort}.sort]
  end

end
