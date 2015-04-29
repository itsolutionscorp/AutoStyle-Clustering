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
    Hash[@db.each_key{ |grade| @db[grade] = @db[grade].sort }.sort]
  end

end
