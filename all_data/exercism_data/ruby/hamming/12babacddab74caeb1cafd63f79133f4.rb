module Hamming

  def self.compute strand, other
    computer = HammingComputer.new strand, other
    computer.distance
  end

  class HammingComputer
    attr_reader :strand, :other

    def initialize strand, other
      valid_range = 0...min_length( strand, other )

      @strand = strand[ valid_range ]
      @other  = other[ valid_range ]
    end

    def pairs
      strand.chars.zip other.chars
    end

    def distance
      pairs.count { |(one, other)| one != other }
    end

    private

    def min_length *strands
      strands.min_by( &:length ).length
    end
  end

end
