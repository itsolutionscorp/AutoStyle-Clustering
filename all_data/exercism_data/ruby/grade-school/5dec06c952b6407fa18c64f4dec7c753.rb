class School
  def initialize
    @school = Hash.new { |school,grade| school[grade] = [] }
  end

  def add(name, grade)
    @school[grade] << name
    @school[grade].sort!
  end

  def grade(grade)
    @school[grade]
  end

  def to_hash
    Hash[@school.sort]
  end
end
