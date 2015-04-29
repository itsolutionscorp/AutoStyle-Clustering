class School

  def initialize    
    @db = Hash.new{|hash, key| hash[key] = Array.new}
  end

  def db
    # prevent changing by returning clone
    @db.clone
  end

  def add(student, grade)
    @db[grade].push(student)
  end

  def grade(g)
    @db[g]
  end

  def sort
    @db.each_key{ |grade| @db[grade] = @db[grade].sort }
    Hash[@db.sort]
  end

end
