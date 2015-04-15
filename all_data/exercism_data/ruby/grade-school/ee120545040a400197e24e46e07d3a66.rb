class School
  attr_reader :school

  def initialize
    @school = Hash.new
  end

  def add(name, grade)
    school[grade] ||= Array.new
    school[grade].push(name).sort!
  end

  def to_hash
    Hash[school.sort]
  end

  def grade(n)
    school[n] || []
  end
end
