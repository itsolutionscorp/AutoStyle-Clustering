class School
  def initialize
    @school = Hash.new {|hash, key| hash[key] = []}
  end
  def to_hash
    Hash[@school.sort]
  end
  def add(name, grade)
    @school[grade] << name
    @school[grade].sort!
  end
  def grade(grade)
    @school[grade]
  end
end
