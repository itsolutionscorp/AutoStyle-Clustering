class School
  def initialize
    @students = Hash.new([])
  end

  def add(name, grade)
    @students.merge!(grade => [name]) { |_, old, new| (old + new).sort }
  end

  def to_hash
    Hash[@students.sort_by(&:first)]
  end

  def grade(n)
    @students[n]
  end
end
