class School
  def initialize
    @students = Hash.new { |k, v| k[v] = [] }
  end

  def add(name, grade)
    @students[grade] << name
  end

  def to_hash
    @students.sort_by { |grade, name| [grade, name.sort!] }.to_h
  end

  def grade(num)
    @students[num].sort || []
  end

end
