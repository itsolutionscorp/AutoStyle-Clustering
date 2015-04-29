class School
  attr_reader :roster
  def initialize
    @roster = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(student, grade)
    roster[grade] << student
    roster[grade].sort!
  end

  def grade(grade)
    roster[grade]
  end

  def to_hash
    Hash[roster.sort_by{|k,v| k}]
  end
end
