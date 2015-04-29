class School
  def initialize
    @school = Hash.new{[]}
  end

  def to_hash
    Hash[school.sort]
  end
  
  def add(name, grade)
    school[grade] = school[grade].push(name).sort!
  end

  def grade(n)
    school[n]
  end

  private
  attr_reader :school
end
