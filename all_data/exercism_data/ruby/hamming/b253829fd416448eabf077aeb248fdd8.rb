require 'set'

class Hamming
  def self.compute(strand1, strand2)
    new(strand1, strand2).compare
  end

  attr_reader :strand1, :strand2, :length
  def initialize(strand1, strand2)
    @length  = [strand1.length, strand2.length].min
    @strand1 = setify(strand1)
    @strand2 = setify(strand2)
  end

  def setify(strand)
    set = strand[0,length].split('').
                           each_with_index.
                           to_set
  end

  def compare
    (strand1 - strand2).count
  end
end
