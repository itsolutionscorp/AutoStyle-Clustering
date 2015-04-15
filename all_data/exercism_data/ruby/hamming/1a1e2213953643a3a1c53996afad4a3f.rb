require "byebug"
class Hamming
  def self.compute(lft, rgt)
    new(lft, rgt).compute
  end

  def initialize(lft, rgt)
    @lft = lft
    @rgt = rgt
  end

  def compute
    total_comparing - total_matching
  end

  private

  def total_comparing
    lft.size
  end

  def total_matching
    lft.chars.keep_if { |char| char == comparison_chars.shift }.size
  end

  def comparison_chars
    @comparison_chars ||= rgt.chars
  end

  attr_reader :lft, :rgt
end
