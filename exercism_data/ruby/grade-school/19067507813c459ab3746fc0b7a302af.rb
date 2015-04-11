# class School
class School
  def initialize
    @students = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(name, grade)
    @students[grade] << name
    @students[grade].sort!
  end

  def grade(g)
    @students[g]
  end

  def to_hash
    Hash[@students.sort]
  end
end
