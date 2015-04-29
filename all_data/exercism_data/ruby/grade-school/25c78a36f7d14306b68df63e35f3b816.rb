class School

  def to_hash
    temp = @students.map do |grade, names|
      [grade, names.sort]
    end.sort
    Hash[temp]
  end

  def add(name, grade)
    @students[grade] << name
  end

  def initialize
    @students = Hash.new { |students, grade| students[grade] = [] }
  end

  def grade(n)
    @students[n].sort
  end

end
