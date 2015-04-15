class School
  def initialize
    @students = Hash.new { |k, v| k[v] = [] }
  end

  def to_hash
    Hash[@students.sort.map { |k, v| [k, v.sort] }]
  end

  def add(name, grade)
    @students[grade] << name
  end

  def grade(grade)
    @students[grade].sort
  end
end
