class School
  def initialize
    @db = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(name, grade)
    @db[grade] << name
    @db[grade].sort!
  end

  def grade(num)
    @db[num]
  end

  def to_hash
    Hash[@db.sort]
  end

end
