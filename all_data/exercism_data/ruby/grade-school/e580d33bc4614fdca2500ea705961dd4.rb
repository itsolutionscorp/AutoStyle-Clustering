class School
  attr_reader :values

  def initialize
    @values = Hash.new { |hash, key| hash[key] = [] }
  end

  def to_hash
    Hash[values.map { |grade, names| [grade, names.sort] }.sort]
  end

  def add(name, grade)
    values[grade].push(name)
  end

  def grade(grade)
    values[grade].sort
  end
end
