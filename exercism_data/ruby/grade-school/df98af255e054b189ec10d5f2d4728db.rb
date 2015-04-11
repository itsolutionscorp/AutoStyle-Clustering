class School
  def initialize
    @roster = Hash.new { |h, k| h[k] = [] }
  end

  def add(student, grade)
    @roster[grade] << student
  end

  def grade(n)
    to_hash.fetch(n, [])
  end

  def to_hash
    @roster.each_value { |v| v.sort! }
    @roster.sort.to_h
  end
end
