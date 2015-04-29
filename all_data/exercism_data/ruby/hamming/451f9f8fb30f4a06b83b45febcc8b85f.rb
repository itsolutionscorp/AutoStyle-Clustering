class Hamming
  def self.compute(strand_a, strand_b)
    return 0 if strand_a == strand_b

    @strand_a = Strand.new(strand_a)
    @strand_b = Strand.new(strand_b)

    hamming = 0
    @strand_a.each_with_index do |char, i|
      break if i == @strand_a.length || i == @strand_b.length
      if char != @strand_b[i]
        hamming += 1
      end
    end
    hamming
  end
end

class Strand
  extend Forwardable
  include Enumerable
  def initialize(strand)
    @strand = strand.chars
  end

  def_delegators :@strand, :each, :length, :[]
end
