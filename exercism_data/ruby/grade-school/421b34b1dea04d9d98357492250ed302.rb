class School
  def initialize
    @db = {}
  end

  def add(name, grade)
    enrol name, grade
  end
    
  def sort
    @roll.sort
    db
  end

  def enrol(name, n); grade(n) << name; end
  
  def grade(n)
    (@db[n] || @db[n] = [])
  end

  def sort
    @db = Hash[@db.sort_by{|grade, name| [grade, name.reverse!]}]
  end

  def db; @db; end
end
