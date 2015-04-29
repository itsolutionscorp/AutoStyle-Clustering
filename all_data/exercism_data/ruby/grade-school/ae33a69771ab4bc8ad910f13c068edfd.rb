class School
  attr_reader :students

  def initialize
    @students = Hash.new { |hash, key| hash[key] = Array.new }
  end

  def add(name, grade)
    students[grade] << name
  end

  def grade(grade)
    students[grade].sort
  end

  def to_hash
    students.keys.sort.each_with_object(Hash.new) do |key, memo|
      memo[key] = grade(key)
    end
  end
end
