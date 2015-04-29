class School
  def initialize
    @db = Hash.new { [] }
  end

  def add(name, grade)
    @db[grade] <<= name
  end

  def to_hash
    Hash[@db.keys.sort.map { |grade| [grade, grade(grade)] }]
  end

  def grade(grade)
    @db[grade].sort
  end
end
