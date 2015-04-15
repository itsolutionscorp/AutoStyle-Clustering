class School
  attr_reader :roster

  def initialize
    @roster = {}
  end

  def add(student, grade)
    roster[grade] ||= []
    roster[grade].push(student).sort!
  end

  def grade(num)
    roster[num] ||= []
  end

  def to_hash
    Hash[roster.sort]
  end
end
