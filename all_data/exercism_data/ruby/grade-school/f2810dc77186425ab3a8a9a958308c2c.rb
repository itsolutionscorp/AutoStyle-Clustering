require 'pry'
class School
  def initialize
    @class = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(student, grade)
    @class[grade].push(student)
  end

  def grade(grade)
    @class[grade].sort
  end

  def to_hash
    # need to sort by grade and student
    out = {}
    keys = @class.keys.sort

    keys.each do |k|
      out[k] = grade(k)
    end

    out
  end
end
