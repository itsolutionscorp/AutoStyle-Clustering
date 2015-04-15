class School
  def initialize
    @role = Role.new
  end

  def add(name, grade)
    @role.enrol name, grade
  end

  def grade(n)
    @role.grade n
  end
  
  def sort
    @role.sort
    db
  end

  def db; @role.to_hash; end
end

class Role
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
