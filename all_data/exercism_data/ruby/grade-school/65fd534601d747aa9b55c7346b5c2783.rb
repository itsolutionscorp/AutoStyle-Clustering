class School
  def initialize
    @roster = {}
  end

  def add(student, grade)
    @roster.has_key?(grade) ? @roster[grade] << student : @roster[grade] = [student]
  end

  def grade(grade)
    @roster[grade] ? @roster[grade].sort : Array.new
  end

  def to_hash
    @roster.each { |_, students| students.sort! }
    Hash[@roster.sort]
  end
end
