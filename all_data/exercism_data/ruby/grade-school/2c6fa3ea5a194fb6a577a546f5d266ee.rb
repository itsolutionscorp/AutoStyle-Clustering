class School
  def initialize
    @roll = Roll.new
  end

  def add(name, grade)
    @roll.enrol name, grade
  end

  def grade(n)
    @roll.grade n
  end
  
  def sort
    @roll.sort
    db
  end

  def db; @roll.to_hash; end
end

class Roll
  def initialize(db = {})
    @db = db
  end

  def enrol(name, n); _grade(n) << name; end
  
  def grade(n); _grade(n); end

  def sort
    @db = Hash[@db.sort_by{|grade, name| [grade, name.reverse!]}]
  end

  def to_hash; @db; end

  private

  def _grade(n)
    (@db[n] || @db[n] = [])
  end
end
