class School

  def initialize
    @students = {}
  end

  def to_hash
    @students.values.each {|students| students.sort!}
    @students
  end

  def add(name, grade)
    @students[grade] ? @students[grade] << name : @students[grade] = [name]
  end

  def grade(grade)
    to_hash[grade] ? to_hash[grade].sort : []
  end
end
