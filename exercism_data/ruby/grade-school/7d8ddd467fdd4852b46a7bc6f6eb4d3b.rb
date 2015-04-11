require 'set'

class School
  def initialize
    @grades = {}
  end

  def to_hash
    Hash[@grades.keys.sort.map { |i| [i, grade(i)] }]
  end

  def add(name, grade)
    @grades[grade] ||= SortedSet.new
    @grades[grade] << name
  end

  def grade(grade)
    (@grades[grade].to_a || [])
  end
end
