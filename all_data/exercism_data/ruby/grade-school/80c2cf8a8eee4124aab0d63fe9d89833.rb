require 'set'

class School
  def initialize
    @scores = Hash.new(SortedSet.new)
  end

  def add(person, grade)
    @scores[grade] += [person]
  end

  def grade(num)
    @scores[num].to_a
  end

  def to_hash
    @scores.sort.each_with_object({}) do |kv, memo|
      memo[kv[0]] = kv[1].to_a
    end
  end
end
