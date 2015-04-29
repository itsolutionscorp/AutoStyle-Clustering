class School
  def initialize
    @data = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(name, grade)
    @data[grade] << name
    @data[grade].sort!
  end

  def grade(n)
    @data[n]
  end

  def to_hash
    @data.sort.to_h
  end
end
