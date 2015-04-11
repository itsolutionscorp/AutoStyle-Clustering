# school.rb
class School
  attr_accessor :roster

  def initialize
    @roster = Hash.new { |h, k| h[k] = [] }
  end

  def add(name, grade)
    roster[grade] << name
    roster[grade].sort!
  end

  def grade(grade)
    roster[grade]
  end

  def to_hash
    roster.sort.to_h
  end
end
