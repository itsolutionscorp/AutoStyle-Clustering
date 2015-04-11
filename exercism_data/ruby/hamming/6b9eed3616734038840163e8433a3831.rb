module Hamming
  def self.compute(strand_one, strand_two)
    strand_one = Strand.new(strand_one)
    strand_two = Strand.new(strand_two)
    strand_one.hamming_distance_with(strand_two)
  end

  class Strand
    attr_reader :nucleotides

    def initialize(nucleotides)
      @nucleotides = nucleotides
    end

    def hamming_distance_with(strand, distance = 0)
      if empty? || strand.empty? || self == strand
        return distance
      end

      if head != strand.head
        distance += 1
      end

      tail.hamming_distance_with(strand.tail, distance)
    end

    def empty?
      nucleotides.empty?
    end

    def == strand
      nucleotides == strand.nucleotides
    end

    def head
      self.class.new(nucleotides.slice(0))
    end

    def tail
      self.class.new(nucleotides.slice(1..-1))
    end
  end
end
