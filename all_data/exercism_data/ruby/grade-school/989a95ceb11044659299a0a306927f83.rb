class School
  def initialize
    @store = Hash.new { [] }
  end

  def add(name, grade)
    @store[grade] = (@store[grade] << name).sort
  end

  def grade(grade)
    @store[grade]
  end

  def to_hash
    Hash[@store.sort]
  end
end
