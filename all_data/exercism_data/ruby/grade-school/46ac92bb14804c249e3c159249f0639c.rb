class School
  def initialize
    @grades = HashOfLists.new
  end

  def add(name, grade)
    @grades[grade] = name
  end

  def grade(grade_name)
    @grades[grade_name]
  end

  def to_hash
    @grades.to_h
  end
end

class HashOfLists
  def initialize
    @db = {}
  end

  def element?(key, val)
    @db.include?(key) && @db[key].include?(val)
  end

  def [](key)
    (@db[key] || []).sort.clone
  end

  def []=(key, val)
    return if element? key, val
    @db.include?(key) ? (@db[key] << val) : (@db[key] = [val])
  end

  def to_h
    Hash[@db.sort].each { |_k, v| v.sort! }
  end
end
