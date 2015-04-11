class Hamming
  def self.compute(a,b)
    (Strand.parse(a) - Strand.parse(b)).count
  end
end

module Hammable
  def -(other)
    self.zip(other).select {|strand_set| Comparison.different?(strand_set)}
  end

  class Comparison
    def self.different?(couple)
      couple.first != couple.last &&
      !couple.last.nil? &&
      !couple.first.nil?
    end
  end
end

require 'delegate'

class Strand < SimpleDelegator
  include Hammable

  def self.parse(strand_string)
    self.new(strand_string.split(//))
  end
end
