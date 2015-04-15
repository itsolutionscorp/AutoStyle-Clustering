class School
  def initialize
    @roster = {}
  end

  def to_hash
    @roster
  end

  def grade(a_grade)
    @roster[a_grade] || []
  end

  def add(name, grade)
    (@roster[grade] ||= []) << name
    @roster[grade].sort!
    @roster = Hash[@roster.sort]
  end
end
