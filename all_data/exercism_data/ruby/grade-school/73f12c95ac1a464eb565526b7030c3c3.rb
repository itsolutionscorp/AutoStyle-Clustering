class School
  def initialize
    @db = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(name, level)
    @db[level] << name
  end

  def grade(level)
    @db[level].sort
  end

  def to_hash
    @db.keys.sort.each_with_object({}) do |level, hash|
      hash[level] = grade(level)
    end
  end
end
