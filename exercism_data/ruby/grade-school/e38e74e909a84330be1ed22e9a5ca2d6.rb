class School
  def initialize
    @students = {}
  end

  def to_hash
    @students
  end

  def add name, grade
    @students[grade].nil? ? @students[grade] = [name] : @students[grade] << name
    @students[grade].sort!
    @students = Hash[@students.sort_by{|k,v| k.to_s.to_i}]
  end

  def grade grade
    @students[grade].nil? ? [] : @students[grade]
  end
end
