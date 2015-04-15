class School

  def initialize
    @school = {}
  end

  def to_hash
    Hash[@school.sort]
  end

  def grade(num)
    @school[num] || []
  end

  def add(name, grade)
    @school[grade] ||= []
    @school[grade] << name
    @school[grade].sort!
  end
end
