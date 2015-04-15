require 'forwardable'

class School

  class ImmutableHash < ::Hash
    def keys
      super.sort
    end

    def to_hash
      super.clone.freeze
    end
  end

  extend Forwardable
  def_delegator :@by_grade, :to_hash

  def initialize
    @by_grade = ImmutableHash.new { |h,k| h[k] = []}
  end

  def add(student, grade)
    by_grade[grade].push student
    by_grade[grade].sort!
  end

  def grade(g)
    by_grade[g].clone
  end

  private

  def by_grade
    @by_grade
  end
end
