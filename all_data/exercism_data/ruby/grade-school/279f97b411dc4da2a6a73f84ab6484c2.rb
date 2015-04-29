class School
  attr_accessor :school
  def initialize
    @school = {}
  end

  def add(name, grade)
    school[grade] ||= []
    school[grade] << name
  end

  def to_hash
    Hash[school.sort do |a, b|
      a[-1].sort!
      a.first <=> b.first
    end]
  end

  def grade(num)
    school[num] ? school[num].sort : []
  end
end
