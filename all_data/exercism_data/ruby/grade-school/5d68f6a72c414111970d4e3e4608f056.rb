class School
  def initialize
    @hash = Hash.new { |h, k| h[k] = [] }
  end

  def add(name, grade)
    @hash[grade] << name
  end

  def grade(grade)
    @hash[grade].sort
  end

  def to_hash
    sorted = @hash.keys.sort.map { |g| [g, grade(g)] }
    Hash[sorted]
  end
end
